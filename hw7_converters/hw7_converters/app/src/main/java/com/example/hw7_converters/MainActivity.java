package com.example.hw7_converters;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements ConvertListFragment.Listener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public void itemClicked(long id) {
        View frame = (FrameLayout) findViewById(R.id.fragment_container);
        String choice = null;
        if(id == 0){
            choice = "fToC";
            Toast.makeText(getApplicationContext(), "YOU PICKED:  " + choice, Toast.LENGTH_SHORT).show();
        }else if(id == 1){
            choice = "mtoK";
            Toast.makeText(getApplicationContext(), "YOU PICKED:  "+ choice, Toast.LENGTH_SHORT).show();
        }else if(id == 2){
            choice = "ytoM";
            Toast.makeText(getApplicationContext(), "YOU PICKED:  "+ choice, Toast.LENGTH_SHORT).show();
        }
        else if(id == 3){
            choice = "gtoL";
            Toast.makeText(getApplicationContext(), "YOU PICKED: "+ choice, Toast.LENGTH_SHORT).show();
        }
        if (frame != null) {
            ConvertDetailFragment workoutDetailFragment = new ConvertDetailFragment();
            workoutDetailFragment.setWorkout(id);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, workoutDetailFragment);
            ft.commit();
        } else {
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, (int) id);
            intent.putExtra(DetailActivity.CHOICE, choice);

            startActivity(intent);
        }
    }
}


