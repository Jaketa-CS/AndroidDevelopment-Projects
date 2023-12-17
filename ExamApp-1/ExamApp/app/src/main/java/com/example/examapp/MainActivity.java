package com.example.examapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static ArrayList<Integer> arrayList;
    ListView listView;
    ArrayAdapter<Integer> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        arrayList = new ArrayList<Integer>();
        arrayAdapter = new ArrayAdapter<Integer>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                arrayList);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               int index = i;
               Integer nummy = arrayList.get(index);

                Integer nNum = getNeg(nummy);
                arrayList.set(i,nNum);
                arrayAdapter.notifyDataSetChanged();

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int index = i;
                arrayList.remove(index);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });

    }
    public static int getNeg(int num) {
        int negVal = (-num);
        return negVal;
    }

    public static int getRandomNum(int minVal, int maxVal) {
        return (new Random()).nextInt((maxVal - minVal) + 1) + minVal;
    }
    public void goNew(View view) {
        System.out.println("NUMBER:"+ getRandomNum(0,9));
        Integer num = getRandomNum(0,9);
        arrayList.add(num);

        arrayAdapter.notifyDataSetChanged();
    }
    public static int getSum(ArrayList<Integer> n) {
        int s = 0;
        n = arrayList;

        for(int i = 0; i < n.size(); i++) {
            s += n.get(i);
        }
        return s;
    }
    public void goSum(View view) {
        int sum = getSum(arrayList);
        Toast.makeText(getApplicationContext(), "SUM:" + sum, Toast.LENGTH_LONG).show();
    }

    public void goCLear(View view) {
        arrayList.clear();
        arrayAdapter.notifyDataSetChanged();
    }
}