package com.example.hw09_bookstore;

public class MyDelivery {
    public  String deliveryTitle;

    public MyDelivery(String cartTitle, String cartAuthor, String cartBook_image) {
        this.deliveryTitle = cartTitle;

    }
    public String getTitle(){return deliveryTitle;}
}
