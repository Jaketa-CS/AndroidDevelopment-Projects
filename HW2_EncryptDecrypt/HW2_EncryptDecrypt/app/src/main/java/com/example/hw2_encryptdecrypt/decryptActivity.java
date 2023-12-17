package com.example.hw2_encryptdecrypt;

import static java.lang.Character.isUpperCase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class decryptActivity extends AppCompatActivity {
    TextView textView;
    TextView percentageView_D;
    SeekBar key;
    ProgressBar progressBar_D;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt);

        progressBar_D = (ProgressBar) findViewById(R.id.progressBar_D);
        textView = (TextView) findViewById(R.id.textView);
        percentageView_D = (TextView) findViewById(R.id.percentageView_D);
        key = (SeekBar) findViewById(R.id.key);

        Intent intent = getIntent();
        String text = intent.getStringExtra("TEXT");
        int num = intent.getIntExtra("NUM",0);
        key.setEnabled(false);
        key.setProgress(num);

        progressBar_D.setProgress(num);

        textView.setText(shiftFunc(text,num));


    }

    StringBuffer shiftFunc(String t, int n) {
        StringBuffer encryption = new StringBuffer();
        String letters = "abcdefghijklmnopqrstuvwxyz";
        char c1;
        char c2;

        for (int i=0; i < t.length(); i++) {
            if (Character.isLowerCase(t.charAt(i))){

                c1 = (char) (((((int) t.charAt(i) + n) - 97) % 26) + 97);
                encryption.append(c1);
            } else {
                c2 = (char) (((((int) t.charAt(i) + n) - 65) % 26) + 65);
                encryption.append(c2);
            }
        }
        return encryption;
    }

    public void click(View view) {
        String text = textView.getText().toString();
        int k = key.getProgress();


        Intent intent = new Intent(this,encryptActivity.class);
        intent.putExtra("TEXT", text);
        intent.putExtra("NUM", k);

        Log.v("HERE", text);
        startActivity(intent);
    }

}