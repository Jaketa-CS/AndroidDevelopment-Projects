package com.example.hw13_nasa_api;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

//api key: etVXwkL8DvBpCaAwqxRnVyFlbxllSAe4rEr0ZnuF
public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, NasaAdapter.myNasaAdapterListener  {
    NasaSQLiteHelper nasaSQLiteHelper;
    RecyclerView recyclerView;

     Handler handler;
     MyHandlerThread myHandlerThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nasaSQLiteHelper = new NasaSQLiteHelper(getApplicationContext());
        NasaAdapter nasaAdapter = new NasaAdapter(nasaSQLiteHelper,this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter((nasaAdapter));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


    }

    public void go(View view) {
        DateFragment dateFragment = new DateFragment(this);
        dateFragment.show(getSupportFragmentManager(),"TIME");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String year = String.format("%d",i);
        String month = String.format("%d",i1);
        String day = String.format("%d",i2);
        Toast.makeText(MainActivity.this, "selected date: "+year +" "+ month +" "+ day, Toast.LENGTH_SHORT).show();

        NasaAsyncTask nasaAsyncTask = new NasaAsyncTask();
        nasaAsyncTask.execute(year,month,day);
    }

    @Override
    public void go(int position) {

    }


    class NasaAsyncTask  extends AsyncTask<String,Void, JSONObject> {
        @SuppressLint("HandlerLeak")
        @Override
        protected JSONObject doInBackground(String... strings) {
            String year = strings[0];
            String month = strings[1];
            String day = strings[2];

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            nasaSQLiteHelper.delete();

            for(int i = 1996; i<=Integer.parseInt(year); i++) {
                String nasa_url = "https://api.nasa.gov/planetary/apod?api_key=P78S3ygIZzBTrpAKUD65fO5HuPrrBLp48YEv287C&date="+i+"-"+month+"-"+day;
                System.out.println("YEARRR:"+i);
                JsonObjectRequest jsonRequestComic = new JsonObjectRequest(Request.Method.GET, nasa_url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String date = response.getString("date");
                                    String img_url = response.getString("url");
                                    Log.v("IMG_URL=",img_url);
                                    Log.v("NASA_DATE=",date);


                                    handler = new Handler(getMainLooper()){//download all the images from  ex: 01/24/1996-01/24/2022
                                        @Override
                                        public void handleMessage(@NonNull Message msg) {
                                            Log.v("HERE", date+".png");
                                            File file = getFileStreamPath(date+".png");
                                            if(file.exists()){
                                                //move to sqlitehelp
                                                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                                                NasaAdapter.nasa_image_view.setImageBitmap(bitmap);
                                            }

                                        }
                                    };
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            myHandlerThread = new MyHandlerThread(getApplicationContext(),handler,date);
                                            myHandlerThread.start();
                                            handler.postDelayed(this,1000);
                                        }
                                    });

                                    nasaSQLiteHelper.insert(date);
                                    recyclerView.getAdapter().notifyDataSetChanged();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, null);
                queue.add(jsonRequestComic);
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            nasaSQLiteHelper.selectAll();
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }
}