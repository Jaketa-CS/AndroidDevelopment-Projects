package com.example.hw2_encryptdecrypt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class encryptActivity extends AppCompatActivity {
    EditText editText;
    private TextView percentageView;
    private ProgressBar progressBar;
    private SeekBar sliderBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextCurse);

        percentageView = (TextView) findViewById(R.id.percentageView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        sliderBar = (SeekBar) findViewById(R.id.sliderBar);


        sliderBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                progressBar.setProgress(progress);
                percentageView.setText(""+progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        Intent intent = getIntent();
        String text = intent.getStringExtra("TEXT");
        int num = intent.getIntExtra("NUM",0);
        sliderBar.setProgress(num);
        progressBar.setProgress(num);

        editText.setText(shiftFunc(text,26 - num));

    }
    StringBuffer shiftFunc(String t, int n) {
        StringBuffer decrypt = new StringBuffer();
        for (int i = 0; i < t.length(); i++) {
            if (Character.isUpperCase(t.charAt(i))) {
                char c1 = (char) (((int) t.charAt(i) + n - 65) % 26 + 65);
                decrypt.append(c1);
            } else {

                char c2 = (char) (((int) t.charAt(i) + n - 97) % 26 + 97);
                decrypt.append(c2);
            }
        }


        return decrypt;
    }

    public void click(View view) {
        String text = editText.getText().toString();
        int key = sliderBar.getProgress();

        Intent intent = new Intent(this,decryptActivity.class);
        intent.putExtra("TEXT", text);
        intent.putExtra("NUM", key);

        Log.v("HERE", text);
        startActivity(intent);
    }
}