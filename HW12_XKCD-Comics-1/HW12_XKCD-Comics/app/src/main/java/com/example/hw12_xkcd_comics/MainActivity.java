package com.example.hw12_xkcd_comics;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements ComicAdapter.myComicAdapterListener{
    ComicSQLiteHelper comicSQLiteHelper;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comicSQLiteHelper = new ComicSQLiteHelper(getApplicationContext());
        ComicAdapter comicAdapter = new ComicAdapter(comicSQLiteHelper,this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter((comicAdapter));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        ComicAsyncTask comicAsyncTask = new ComicAsyncTask();
        comicAsyncTask.execute();//100

    }

    @Override
    public void go(int position) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_COMICID, position);
        Log.v("VALLL: ", String.valueOf(position));
        intent.putExtra("ID", position);
        startActivity(intent);
    }


    class ComicAsyncTask  extends AsyncTask<Integer,Void,JSONObject>  {

        @Override
        protected JSONObject doInBackground(Integer... integers) {
           // comicSQLiteHelper.delete();

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String urlCurr = "https://xkcd.com/info.0.json";
                JsonObjectRequest  jsonRequest  = new JsonObjectRequest(Request.Method.GET, urlCurr, null,
                        new Response.Listener<JSONObject>(){
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    int recent_comic = response.getInt("num");
                                    for (int i = 0; i < 100; i++){

                                        String url = "https://xkcd.com/"+recent_comic+"/info.0.json";
                                        JsonObjectRequest jsonRequestComic = new JsonObjectRequest(Request.Method.GET, url, null,
                                                new Response.Listener<JSONObject>() {
                                                    @Override
                                                    public void onResponse(JSONObject response) {
                                                        try {
                                                            int number = response.getInt("num");
                                                            String title = response.getString("title");
                                                            String cartoon = response.getString("img");

                                                            comicSQLiteHelper.insert(number, title, cartoon);
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                }, null);

                                        queue.add(jsonRequestComic);
                                        recent_comic--;
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },null);

                queue.add(jsonRequest);


            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            comicSQLiteHelper.selectAll();
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }
}
