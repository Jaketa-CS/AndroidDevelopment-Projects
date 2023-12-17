package com.example.cc11_numbersqlitehelper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ColorSQLiteHelper colorSQLiteHelper;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView textViewSize;
    String size = String.valueOf(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        progressBar = findViewById(R.id.progressBar);
        textViewSize = findViewById(R.id.textViewSize);

    }


//INSERT N COLORS-------------------------------------------------------------------------
    class InsertColorAsyncTask extends AsyncTask<Integer,Integer,Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            int ints_size = integers[0];
            int dbSize = Integer.parseInt(size);

            progressBar.setMax(dbSize);
            textViewSize.setText(size);
                   for (int i = 0; i < ints_size; i++){
                       colorSQLiteHelper.insert(Integer.parseInt(size));
                        publishProgress(i);

            }
            return null;
        }
        protected void onProgressUpdate(Integer... values){
            super.onProgressUpdate(values);
            int progress = values[0];
            progressBar.setProgress(progress);
        }

        @Override
        protected void onPostExecute(Void unused) {
            colorSQLiteHelper.selectAll();
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }
    //--------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.addMenuItem){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            input.setRawInputType(Configuration.KEYBOARD_12KEY);
            builder.setView(input).setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            size = input.getText().toString();
                            Toast.makeText(MainActivity.this, "ADD ("+size+") RANDOM COLORS", Toast.LENGTH_SHORT).show();

                            colorSQLiteHelper = new ColorSQLiteHelper(getApplicationContext());
                            ColorAdapter numberAdapter = new ColorAdapter(colorSQLiteHelper);
                            recyclerView.setAdapter((numberAdapter));
                            InsertColorAsyncTask insertNumberAsyncTask = new InsertColorAsyncTask();
                            if (size == null || size.isEmpty() ){
                                size = String.valueOf(0);
                                insertNumberAsyncTask.execute(Integer.parseInt(size));
                            }else{
                                insertNumberAsyncTask.execute(Integer.parseInt(size));
                            }
                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
            builder.show();
            return true;
        }else if(id == R.id.sortMenuItem){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            input.setRawInputType(Configuration.KEYBOARD_12KEY);
            builder.setTitle("SORT BY: ").setItems(R.array.sort_array, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        if (textViewSize.getText() == ""){
                            Toast.makeText(MainActivity.this, "empty/uninitialized!!", Toast.LENGTH_SHORT).show();

                        }else{
                            if (id == 0){
                                Toast.makeText(MainActivity.this, "sort by R", Toast.LENGTH_SHORT).show();
                                colorSQLiteHelper.sortR();
                                recyclerView.getAdapter().notifyDataSetChanged();
                            }else if (id == 1){
                                Toast.makeText(MainActivity.this, "sort by G", Toast.LENGTH_SHORT).show();
                                colorSQLiteHelper.sortG();
                                recyclerView.getAdapter().notifyDataSetChanged();
                            }else if (id == 2){
                                Toast.makeText(MainActivity.this, "sort by B", Toast.LENGTH_SHORT).show();
                                colorSQLiteHelper.sortB();
                                recyclerView.getAdapter().notifyDataSetChanged();
                            }else if (id == 3){
                                Toast.makeText(MainActivity.this, "sort by Favorites", Toast.LENGTH_SHORT).show();
                                colorSQLiteHelper.sortFav();
                                recyclerView.getAdapter().notifyDataSetChanged();
                            }else if (id == 4){
                                Toast.makeText(MainActivity.this, "sort by Default", Toast.LENGTH_SHORT).show();
                                colorSQLiteHelper.sortFav();
                                recyclerView.getAdapter().notifyDataSetChanged();
                            }
                        }

                        }
                    })

                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
            builder.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public int getItemCount() {
        return colorSQLiteHelper.getSize();
    }
}