package com.example.hw09_bookstore;

import java.util.ArrayList;

public class Book {
    public  String title;
    public String author;
    public int book_image;

    public static ArrayList<Book> booksList = new ArrayList<Book>();

     static {
         booksList.add(new Book("The Fountain Head", "Ayn Rand", R.drawable.fountainhead));
         booksList.add(new Book("Sometimes a Great Notion", "Ken Kesey", R.drawable.sometimesagreatnotion));
         booksList.add(new Book("American Psycho", "Bret Easton Ellis", R.drawable.americanpsycho));
    }

    public Book(String title, String author, int book_image) {
        this.title = title;
        this.author = author;
        this.book_image = book_image;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){ return author;}
    public int getBook_image(){ return book_image; }

    public String toString(){return this.title + this.author + this.book_image;}

}
