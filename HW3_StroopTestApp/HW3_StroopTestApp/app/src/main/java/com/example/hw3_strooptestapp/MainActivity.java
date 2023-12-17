package com.example.hw3_strooptestapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final String Key = "MY_KEY";
    private final Random random = new Random();

    int red = Color.RED;//0
    int green = Color.GREEN;//1
    int blue = Color.BLUE;//2
    int b = -16776961;
    int r = -65536;
    int g = -16711936;
    int index;
    int rColNum;
    String rColWord;

    private int rightCounter;
    private int wrongCounter;

    int[] colorsArray = {red,green,blue};
    String[] colorNamesArray = {"RED","GREEN","BLUE"};

    TextView colorText;
    Button redButton, greenButton, blueButton;
    TextView rightCount;
    TextView wrongCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(Key,"onCreate method called");

        colorText = (TextView) findViewById(R.id.colorText);

        redButton = (Button) findViewById(R.id.redButton);
        greenButton = (Button) findViewById(R.id.greenButton);
        blueButton = (Button) findViewById(R.id.blueButton);

        rightCount = (TextView) findViewById(R.id.rightCount);
        wrongCount = (TextView) findViewById(R.id.wrongCount);

       if(savedInstanceState != null){
           rColWord = savedInstanceState.getString("rColWord");

           rightCounter = savedInstanceState.getInt("rightCounter");
           wrongCounter = savedInstanceState.getInt("wrongCounter");

           rightCount.setText((String.valueOf(rightCounter)));
           wrongCount.setText((String.valueOf(wrongCounter)));
       }
        wordScramblr();

    }
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println(" onSaveInstanceState( .. ): " + rightCounter);

        outState.putInt("rightCounter",rightCounter);
        outState.putInt("wrongCounter",wrongCounter);

    }

    public void wordScramblr(){
        Random random = new Random();
        index = random.nextInt(colorNamesArray.length);

        rColWord = colorNamesArray[index];
        rColNum = colorsArray[random.nextInt(3)];

        colorText.setText(rColWord);
        colorText.setTextColor(rColNum);
    }

    public void clickRed(View view) {
        Log.v("R! ", String.valueOf(this));
        if (rColNum == r){
            rightCounter++;
            rightCount.setText((String.valueOf(rightCounter)));
            wordScramblr();
        }
        else if (rColNum == g) {
            wrongCounter++;
            wrongCount.setText((String.valueOf(wrongCounter)));
            wordScramblr();
        } else if (rColNum == b) {
            wrongCounter++;
            wrongCount.setText((String.valueOf(wrongCounter)));
            wordScramblr();
        }
        wordScramblr();
    }

    public void clickGreen(View view) {
        Log.v("G! ", String.valueOf(this));
        if (rColNum == g){
            rightCounter++;
            rightCount.setText((String.valueOf(rightCounter)));
            wordScramblr();
        }
        else if (rColNum == r) {
           wrongCounter++;
            wrongCount.setText((String.valueOf(wrongCounter)));
            wordScramblr();
        } else if (rColNum == b) {
            wrongCounter++;
            wrongCount.setText((String.valueOf(wrongCounter)));
            wordScramblr();
        }
    }

    public void clickBlue(View view) {
        Log.v("B! ", String.valueOf(this));

        if (rColNum == b){
            rightCounter++;
            rightCount.setText((String.valueOf(rightCounter)));
            wordScramblr();
        }
        else if (rColNum == r) {
            wrongCounter++;
            wrongCount.setText((String.valueOf(wrongCounter)));
            wordScramblr();
        } else if (rColNum == g) {
            wrongCounter++;
            wrongCount.setText((String.valueOf(wrongCounter)));
            wordScramblr();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.v(Key,"onStart method called");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(Key,"onResume method called");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(Key,"onPause method called");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(Key,"onStop method called");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(Key,"onRestart method called");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setContentView(R.layout.activity_main);

        Log.v(Key,"onDestroy method called");
    }
}