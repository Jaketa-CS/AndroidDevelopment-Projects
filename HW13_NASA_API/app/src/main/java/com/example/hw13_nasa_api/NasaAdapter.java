package com.example.hw13_nasa_api;

import android.annotation.SuppressLint;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;

public class NasaAdapter extends RecyclerView.Adapter {

    NasaSQLiteHelper nasaSQLiteHelper;
    myNasaAdapterListener myNasaAdapterListener;
    static ImageView nasa_image_view;

    private Handler handler;
    public interface myNasaAdapterListener {
        void go(int position);
    }
    public NasaAdapter(NasaSQLiteHelper nasaSQLiteHelper, myNasaAdapterListener myNasaAdapterListener) {
        this.nasaSQLiteHelper = nasaSQLiteHelper;
        this.myNasaAdapterListener = myNasaAdapterListener;
        
    }

    public class NasaViewHolder extends RecyclerView.ViewHolder{

        TextView textViewDate;

        public NasaViewHolder(@NonNull View itemView) {
            super(itemView);
            nasa_image_view = itemView.findViewById(R.id.nasa_image_view);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            Log.v("HEEYY","HEREE");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    myNasaAdapterListener.go(position);
                }
            });

        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_nasa_layout, parent,false);

        return new NasaViewHolder(view);
    }
    @SuppressLint("HandlerLeak")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String date = nasaSQLiteHelper.getDate(position);

        NasaViewHolder nasaViewHolder = (NasaViewHolder) holder;
        nasaViewHolder.textViewDate.setText(date);

        //nasaViewHolder.nasa_image_view.setImageResource(img);

//       // nasa_image_view.setImageBitmap(bitmap);
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                nasaViewHolder.nasa_image_view.setImageResource(date);
//                handler.postDelayed(this,1000);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return nasaSQLiteHelper.getCount();
    }

}
