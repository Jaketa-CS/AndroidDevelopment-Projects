package com.example.hw8_tasklist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyTaskAdapter.myTaskAdapterListener {

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
        recyclerView.setAdapter(new MyTaskAdapter(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
    public void addTask(View view) {
        Intent intent = new Intent(getApplicationContext(), MakeTaskActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Add Task", Toast.LENGTH_SHORT).show();
    }

    public void go(int position){
        myTask myTask = Task.myTasks.get(position);

        Intent intent = new Intent(getApplicationContext(), MakeReviewActivity.class);
        intent.putExtra("TASK", position);
        startActivity(intent);


        Toast.makeText(getApplicationContext(),"review"+position, Toast.LENGTH_SHORT).show();
    }

}
