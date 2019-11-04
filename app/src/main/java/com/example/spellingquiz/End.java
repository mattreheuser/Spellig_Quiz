package com.example.spellingquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class End extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        createScore();
    }

    public void createScore(){
        TextView score = findViewById(R.id.textViewScore);
        int totalScore = getIntent().getExtras().getInt("Total Score");
        Log.i("info", ""+totalScore);
        //score.setText(Integer.toString(totalScore));
    }
}
