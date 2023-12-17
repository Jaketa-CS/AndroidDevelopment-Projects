package com.example.hw7_converters;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_WORKOUT_ID = "id";
    public static final String CHOICE = "choice";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        int workoutId =(int) getIntent().getExtras().get(EXTRA_WORKOUT_ID);

        ConvertDetailFragment workoutDetailFragment = (ConvertDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_frag);
        workoutDetailFragment.setWorkout(workoutId);
    }
}
