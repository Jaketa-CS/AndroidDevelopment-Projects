package com.example.hw5_favorites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> arrayList;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                arrayList);
        //assosiate adapter w/ listview
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String phrase = Model.arrayList.get(i);
                Toast.makeText(getApplicationContext(), phrase, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void go(View view){
        EditText editText = findViewById(R.id.editText);
        Toast.makeText(getApplicationContext(), editText.getText(), Toast.LENGTH_SHORT).show();
        String phrase = String.valueOf(editText.getText());
        arrayList.add(phrase);
        arrayList.add(phrase);
        arrayList.add(phrase);
        arrayList.remove(phrase);
        arrayAdapter.notifyDataSetChanged();
    }
}