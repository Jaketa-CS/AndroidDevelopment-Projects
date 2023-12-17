package com.example.hw4_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView inputView;
    TextView operationView;

    Button button0,button1,button2,
            button3,button4,button5,
            button6,button7, button8,button9;

    Button buttonAdd,buttonSub,buttonDiv,buttonMult;
    Button buttonC,buttonModulus, buttonExpo,buttonPM,buttonPoint;
    Button buttonEquals;

    boolean operation = true;
    boolean negative = false;
    String num1 = "";
    String currentOperation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputView = findViewById(R.id.inputView);
        operationView = findViewById(R.id.operationView);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSub = findViewById(R.id.buttonSub);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonMult = findViewById(R.id.buttonMult);

        buttonC = findViewById(R.id.buttonClear);
        buttonModulus = findViewById(R.id.buttonModulus);
        buttonExpo = findViewById(R.id.buttonExpo);
        buttonPM = findViewById(R.id.buttonPM);
        buttonPoint = findViewById(R.id.buttonPoint);

        buttonEquals = findViewById(R.id.buttonEquals);
        disable();
    }
    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        Button button = (Button) view;
        String buttonValue = button.getText().toString();
        String buttonNum = inputView.getText().toString();
        switch (view.getId()){
            case R.id.button0:
                buttonNum += "0";
                enable();

                break;
            case R.id.button1:
                buttonNum += "1";
                enable();

                break;
            case R.id.button2:
                buttonNum += "2";
                enable();

                break;
            case R.id.button3:
                buttonNum += "3";
                enable();

                break;
            case R.id.button4:
                buttonNum += "4";
                enable();

                break;
            case R.id.button5:
                buttonNum += "5";
                enable();

                break;
            case R.id.button6:
                buttonNum += "6";
                enable();

                break;
            case R.id.button7:
                buttonNum += "7";
                enable();

                break;
            case R.id.button8:
                buttonNum += "8";
                enable();

                break;
            case R.id.button9:
                buttonNum += "9";
                enable();

                break;

            case R.id.buttonPM:
                    if(!negative){
                        buttonNum = "-" + buttonNum;
                        negative = true;

                    }else{
                        buttonNum = buttonNum.replace("-", "");
                        buttonNum = "" + buttonNum;
                        negative = false;

                    }
                break;

            case R.id.buttonPoint:
                buttonNum += ".";
                break;
            case R.id.buttonClear:
                buttonNum = "";
                operationView.setText("");
                break;
        }
        inputView.setText(buttonNum);

        if(operation){
            inputView.setText("");
        }
        operation = false;

    }
    public void operationClick(View view){
        operation = true;
        num1 = inputView.getText().toString();
        int id = view.getId();
        if (id == R.id.buttonAdd) {
            currentOperation = "+";
            inputView.setText("");
           disable();
        } else if (id == R.id.buttonSub) {
            currentOperation = "-";
            inputView.setText("");
            disable();

        } else if (id == R.id.buttonDiv) {
            currentOperation = "/";
            inputView.setText("");
            disable();

        } else if (id == R.id.buttonMult) {
            currentOperation = "*";
            inputView.setText("");
            disable();

        } else if (id == R.id.buttonModulus) {
            currentOperation = "%";
            inputView.setText("");
            disable();

        }else if (id == R.id.buttonExpo) {
            currentOperation = "^";
            inputView.setText("");
            disable();

        }
        operationView.setText(currentOperation);
    }

    public void enable(){
        buttonAdd.setEnabled(true);
        buttonMult.setEnabled(true);
        buttonDiv.setEnabled(true);
        buttonModulus.setEnabled(true);
        buttonSub.setEnabled(true);
        buttonExpo.setEnabled(true);
        buttonEquals.setEnabled(true);
    }
    public void disable(){
        buttonAdd.setEnabled(false);
        buttonMult.setEnabled(false);
        buttonDiv.setEnabled(false);
        buttonModulus.setEnabled(false);
        buttonSub.setEnabled(false);
        buttonExpo.setEnabled(false);
        buttonEquals.setEnabled(false);
    }
    public void equalsClick(View view) {
        String result = inputView.getText().toString();
        double number = Double.parseDouble(result);

        if ("+".equals(currentOperation)) {
            number = Double.parseDouble(num1) + Double.parseDouble(result);
        } else if ("-".equals(currentOperation)) {
            number = Double.parseDouble(num1) - Double.parseDouble(result);
        } else if ("*".equals(currentOperation)) {
            number = Double.parseDouble(num1) * Double.parseDouble(result);
        } else if ("/".equals(currentOperation)) {
            number = Double.parseDouble(num1) / Double.parseDouble(result);
        } else if ("%".equals(currentOperation)) {
            number = Double.parseDouble(num1) % Double.parseDouble(result);
        }
        else if ("^".equals(currentOperation)) {
            number = Math.pow(Double.parseDouble(num1), Double.parseDouble(result));
        }

        if (result.equals("0")){
            inputView.setText("Not Defined");
        }else{
            inputView.setText(number + "");
        }
        operationView.setText("");



    }
}