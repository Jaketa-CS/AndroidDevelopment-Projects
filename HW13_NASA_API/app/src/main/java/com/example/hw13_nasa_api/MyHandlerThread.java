package com.example.hw13_nasa_api;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MyHandlerThread extends HandlerThread {
    private  String date;
    private Handler mainHandler;
    private Context context;

    public MyHandlerThread(Context context, Handler mainHandler, String date) {
        super("MyHandlerThread");
        this.context = context;
        this.mainHandler = mainHandler;
        this.date = date;
    }


    @Override
    public void run() {
        Log.v("HEREEE",date);

            try {
                URL url = new URL("https://api.nasa.gov/planetary/apod?api_key=P78S3ygIZzBTrpAKUD65fO5HuPrrBLp48YEv287C&date="+date);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                String line = bufferedReader.readLine();
                JSONObject jsonObject = new JSONObject(line);
                String img = jsonObject.getString("url");

                //Create Bitmap (image) from URL
                URL imgURL = new URL(img);
                InputStream inputStream = imgURL.openStream();

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();

                //Save Bitmap file to app’s local file storage
                FileOutputStream fileOutputStream = context.openFileOutput(date+".png",Context.MODE_PRIVATE);
                bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
                fileOutputStream.close();

                mainHandler.sendEmptyMessage(0);

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }


    }
}