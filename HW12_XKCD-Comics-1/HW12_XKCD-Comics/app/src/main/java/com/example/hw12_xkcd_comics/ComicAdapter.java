package com.example.hw12_xkcd_comics;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ComicAdapter extends RecyclerView.Adapter  {
    ComicSQLiteHelper comicSQLiteHelper;
    myComicAdapterListener myComicAdapterListener;

    public interface myComicAdapterListener {
        public void go(int position);
    }

    public ComicAdapter(ComicSQLiteHelper comicSQLiteHelper,myComicAdapterListener myComicAdapterListener) {
        this.comicSQLiteHelper = comicSQLiteHelper;
        this.myComicAdapterListener = myComicAdapterListener;
    }
    public class ComicViewHolder extends RecyclerView.ViewHolder{
        WebView webViewCartoon;
        TextView numberView;
        TextView titleView;
        public ComicViewHolder(@NonNull View itemView) {
            super(itemView);
            webViewCartoon = itemView.findViewById(R.id.webViewCartoon);
            numberView = itemView.findViewById(R.id.numberView);
            titleView = itemView.findViewById(R.id.titleView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    myComicAdapterListener.go(position);
                }
            });

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_comic_layout, parent,false);
        return new ComicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int number = comicSQLiteHelper.getNumber(position);
        String title = comicSQLiteHelper.getTitle(position);
        String cartoon = comicSQLiteHelper.getCartoon(position);

        ComicViewHolder comicViewHolder = (ComicViewHolder) holder;
        comicViewHolder.numberView.setText(String.valueOf(number));
        comicViewHolder.titleView.setText(title);
        comicViewHolder.webViewCartoon.loadUrl(cartoon);

    }

    @Override
    public int getItemCount() {
        return comicSQLiteHelper.getCount();
    }


}
