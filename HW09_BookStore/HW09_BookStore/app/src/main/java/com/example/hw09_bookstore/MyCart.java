package com.example.hw09_bookstore;

import androidx.annotation.NonNull;

public class MyCart {
    public  String cartTitle;
    public String cartAuthor;
    public String cartBook_image;


    public MyCart(String cartTitle, String cartAuthor, String cartBook_image) {
        this.cartTitle = cartTitle;
        this.cartAuthor = cartAuthor;
        this.cartBook_image = cartBook_image;
    }

    public String getTitle(){return cartTitle;}
    public String getAuthor(){ return cartAuthor;}
    public String getBook_image(){ return cartBook_image; }

    public String toString(){return String.valueOf(this.cartBook_image);}


}
