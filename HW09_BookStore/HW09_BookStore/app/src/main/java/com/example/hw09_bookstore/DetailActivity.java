package com.example.hw09_bookstore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DetailActivity extends AppCompatActivity implements CartAdapter.myCartAdapterListener {
    public static final String BOOK_DETAIL_ID = "ID";
    public static final String BOOK_DATE = "DATE";
    public static final String BOOK_TIME = "TIME";

    RecyclerView recyclerViewDetail;
    TextView textViewDelivery;

    @Override
    protected void onStart() {
        super.onStart();
        recyclerViewDetail.getAdapter().notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String time = (String)getIntent().getExtras().get(BOOK_TIME);;
        String date = (String)getIntent().getExtras().get(BOOK_DATE);;


        recyclerViewDetail = findViewById((R.id.recyclerViewDetail));
        recyclerViewDetail.setAdapter(new CartAdapter(this));
        recyclerViewDetail.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        textViewDelivery = findViewById((R.id.textViewDelivery));
        textViewDelivery.setText(String.format("Delivery set for %s at %s",date,time));


    }


    @Override
    public void go(int position) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Cart.myCartBooks.clear();

        finish();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
