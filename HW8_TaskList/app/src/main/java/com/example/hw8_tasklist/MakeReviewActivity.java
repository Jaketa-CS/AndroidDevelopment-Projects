package com.example.hw8_tasklist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MakeReviewActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner prioritySpinner;
    private EditText inputName;
    private EditText inputDesc;
    int task_index_rev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_task_layout);

        myTask task = Task.myTasks.get(task_index_rev);
        task_index_rev = getIntent().getIntExtra("TASK",1);

        prioritySpinner = findViewById(R.id.spinner);
        inputName = findViewById(R.id.editTextName);
        inputDesc = findViewById(R.id.editTextDesc);

        inputName.setText(String.valueOf(task.name));
        inputDesc.setText(String.valueOf(task.desc));

        prioritySpinner.setOnItemSelectedListener(this);
        String[] priorityRange = getResources().getStringArray(R.array.range);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, priorityRange);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(adapter);
        prioritySpinner.setSelection(Integer.parseInt(task.priorty) -1);


    }
    //SPINNER---------------------------------------------------------------------------------------
    @Override
    public void onItemSelected(AdapterView<?> parent, View View, int position, long id){
        if (parent.getId() == R.id.spinner){
            String val = parent.getItemAtPosition(position).toString();
            Toast.makeText(this, "Priority:"+val, Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent){

    }
    //BACK BUTTON----------------------------------------------------------------------------------------
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "BACK:", Toast.LENGTH_SHORT).show();
        String name = inputName.getText().toString();
        String desc = inputDesc.getText().toString();
        String spin = prioritySpinner.getSelectedItem().toString();

        Task.myTasks.set(task_index_rev, new myTask(name,desc,spin));
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    //DELETE----------------------------------------------------------------------------------------
    public void remove(View view){
        int task_index;
        task_index = getIntent().getIntExtra("TASK",1);
        myTask task = Task.myTasks.get(task_index);
        Toast.makeText(getApplicationContext(), "delete" + task, Toast.LENGTH_LONG).show();

        Task.myTasks.remove(task);


        finish();

    }

}
