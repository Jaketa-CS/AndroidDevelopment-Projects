package com.example.hw8_tasklist;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MakeTaskActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner prioritySpinner;
    private EditText inputName;
    private EditText inputDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_task_layout);

        Intent intent = getIntent();
        int position = intent.getIntExtra("POSITION",0);

        prioritySpinner = findViewById(R.id.spinner);
        inputName = findViewById(R.id.editTextName);
        inputDesc = findViewById(R.id.editTextDesc);


        prioritySpinner.setOnItemSelectedListener(this);

        String[] priorityRange = getResources().getStringArray(R.array.range);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, priorityRange);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(adapter);

    }
    //spinner---------------------------------------------------------------------------------------
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

    //SUBMIT----------------------------------------------------------------------------------------
    public void add(View view){
      String name = inputName.getText().toString();
      String desc = inputDesc.getText().toString();
        String spin = prioritySpinner.getSelectedItem().toString();

        if (TextUtils.isEmpty(name) ){
            Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
      }else if (TextUtils.isEmpty(desc) ){
            Toast.makeText(this, "Description cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else{
          Task.myTasks.add(new myTask(name,desc,spin));
          
          Intent intent = new Intent(getApplicationContext(), MainActivity.class);
          startActivity(intent);
      }

    }
}
