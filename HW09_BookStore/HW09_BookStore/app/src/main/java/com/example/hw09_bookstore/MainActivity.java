package com.example.hw09_bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements BookAdapter.myBookAdapterListener {
    RecyclerView recyclerView;

    @Override
    protected void onStart() {
        super.onStart();
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById((R.id.recyclerView));
        recyclerView.setAdapter(new BookAdapter(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
    public void go(int position){
        Intent intent = new Intent(getApplicationContext(), BookActivity.class);
        intent.putExtra("BOOK: ", position);
        intent.putExtra(BookActivity.BOOK_ID, (int) position);

        startActivity(intent);
    }

    //toolbar---------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.cart_toolbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.cartMenuItem:
                Intent intent = new Intent(this, CartActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}