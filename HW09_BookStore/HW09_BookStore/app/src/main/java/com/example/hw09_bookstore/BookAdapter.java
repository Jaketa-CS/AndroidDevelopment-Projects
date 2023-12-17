package com.example.hw09_bookstore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookAdapter extends RecyclerView.Adapter {
    public interface myBookAdapterListener {
        public void go(int position);
    }
    myBookAdapterListener myBookAdapterListener;

    public BookAdapter(myBookAdapterListener bookAdapterListener) {
        this.myBookAdapterListener = bookAdapterListener;
    }

    public class MyBookViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewPhoto;
        TextView textViewTitle;
        TextView textViewAuthor;
        public MyBookViewHolder(@NonNull View itemView){
            super(itemView);

            imageViewPhoto = itemView.findViewById(R.id.imageViewPhoto);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);

            imageViewPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    myBookAdapterListener.go(position);
                }
            });
        }
        public void updateTextViews(Book myBook){
            imageViewPhoto.setImageResource(myBook.book_image);
            textViewTitle.setText(String.valueOf(myBook.title));
            textViewAuthor.setText(String.valueOf(myBook.author));

        }
    }
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_layout,parent, false);

        return new MyBookViewHolder(view);
    }
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){
        MyBookViewHolder myBookViewHolder = (MyBookViewHolder) holder;
        Book myBook = Book.booksList.get(position);
        myBookViewHolder.updateTextViews(myBook);
    }
    public int getItemCount(){
        return Book.booksList.size();
    }
}
