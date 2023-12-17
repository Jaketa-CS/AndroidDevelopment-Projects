package com.example.hw09_bookstore;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CartActivity extends AppCompatActivity implements CartAdapter.myCartAdapterListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    RecyclerView recyclerViewCart;
    String time = "";
    String date = "";
    @Override
    protected void onStart() {
        super.onStart();
        recyclerViewCart.getAdapter().notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerViewCart = findViewById((R.id.recyclerViewCart));
        recyclerViewCart.setAdapter(new CartAdapter(this));
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    //toolbar---------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.trash_toolbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.trashMenuItem:MenuItem:
                Toast.makeText(this, "Items Removed...", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setTitle(String.format("REMOVE ALL ITEMS FROM THE CART"));
                builder.setMessage("Are you sure? :(").setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Cart.myCartBooks.clear();
                                finish();
                                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builder.show();


                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //-----------------------------------------------------------------------------------------
    @Override
    public void go(int position) {

    }

    public void dateClick(View view) {
        DateFragment dateFragment = new DateFragment(this);
        dateFragment.show(getSupportFragmentManager(),"DATE");
    }

    public void timeClick(View view) {
        TimeFragment timeFragment = new TimeFragment(this);
        timeFragment.show(getSupportFragmentManager(),"TIME");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        date = String.format("%d/%d/%d",i1,i2,i);
        Toast.makeText(CartActivity.this, "date: "+date, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        time = String.format("%d:%d",i,i1);
        Toast.makeText(CartActivity.this, "time: "+time, Toast.LENGTH_SHORT).show();


    }

    public void submitClick(View view) {

        if (date.equals("") || time.equals("")){
            Toast.makeText(this, "Missing DATE and or TIME!", Toast.LENGTH_SHORT).show();
        }else if(Cart.myCartBooks.isEmpty()){
            Toast.makeText(this, "Cart is EMPTY!!", Toast.LENGTH_SHORT).show();

        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
            builder.setTitle(String.format("Delivery set for %s at %s", date, time));
            builder.setMessage("Continue?").setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                            intent.putExtra("ID", id);
                            intent.putExtra("DATE", date);
                            intent.putExtra("TIME", time);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
            builder.show();
        }
    }
}