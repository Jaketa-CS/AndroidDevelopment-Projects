package com.example.hw7_converters;


import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ConvertDetailFragment extends Fragment implements View.OnClickListener {

    private long workoutId = 0;
    private TextView textTitle;
    private TextView textViewConvert;
    private TextView textViewConverted;
    private Button fragmentButton;
    private EditText editTextInput;
    private TextView textViewResult;
    String choice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_convert_detail,container,false);

        textTitle = view.findViewById(R.id.textTitle);
        textViewConvert = view.findViewById(R.id.textViewConvert);
        textViewConverted = view.findViewById(R.id.textViewConverted);
        textViewResult = view.findViewById(R.id.textViewResult);
        editTextInput = view.findViewById(R.id.editTextInput);
        fragmentButton = (Button) view.findViewById(R.id.fragmentButton);
        fragmentButton.setOnClickListener(this);
        return view;
    }

    public void setWorkout(long id){
        this.workoutId = id;
    }

    @Override
    public void onStart(){
        super.onStart();

        Convert convert = Convert.conversions[(int) workoutId];
        textTitle.setText(convert.getName());
        textTitle.setBackgroundColor(Color.CYAN);


        textViewConvert.setText(convert.getC1());
        textViewConverted.setText(convert.getC2());



    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){savedInstanceState.putLong("workoutId",workoutId);}

    @Override
    public void onClick(View view) {
        String result;
        double number;
        if(TextUtils.isEmpty(editTextInput.getText().toString())) {
            editTextInput.setHint("EMPTY >;(");

            number = Double.parseDouble("0");
        }else{
            result = editTextInput.getText().toString();
            number = Double.parseDouble(result);

            if (textTitle.getText() == "Fahrenheit to Celsius") {
                number = (number - 32) * 5 / 9;
            } else if (textTitle.getText() =="Miles to Kilometers") {
                number = number * 1.609344;
            } else if (textTitle.getText() == "Yards to Meters") {
                number = number * 0.9144;
            } else {
                number = number * 3.7854;
            }

        }

        textViewResult.setText(String.format("%.2f", number ));

    }
}
