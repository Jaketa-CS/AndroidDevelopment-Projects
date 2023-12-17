package com.example.hw09_bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookActivity extends AppCompatActivity {
    public static final String BOOK_ID = "id";
    ImageView imageViewPhoto;
    TextView textViewTitle;
    TextView textViewAuthor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        //Get the book from the intent
        int selectedBook = (Integer)getIntent().getExtras().get(BOOK_ID);
        Book book = Book.booksList.get(selectedBook);

        //book image
        imageViewPhoto = findViewById(R.id.imageViewPhoto);
        imageViewPhoto.setImageResource(book.getBook_image());
        imageViewPhoto.setContentDescription(book.getTitle());

        //book name
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewTitle.setText(book.getTitle());

        //book author
        textViewAuthor = findViewById(R.id.textViewAuthor);
        textViewAuthor.setText(book.getAuthor());

    }

    public void addClick(View view) {
    String photo = imageViewPhoto.toString();
    String title = (String) textViewTitle.getText();
    String author = String.valueOf(textViewAuthor);

        Cart.myCartBooks.add(new MyCart(title,author,photo));

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
