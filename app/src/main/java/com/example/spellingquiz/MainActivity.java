package com.example.spellingquiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static android.graphics.BlendMode.COLOR;

public class MainActivity extends AppCompatActivity {

    private int totalCorrect = 0;
    private int totalAnswered;
    private ArrayList<Word> words = new ArrayList<Word>(10);
    private int current = -1;
    private Boolean isCorrect;
    private Word currentWord;
    private TextView time;
    private int secondsLeft = 60*5;
    private TextView end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startTimer();
        createPage();
        nextQuestion();
    }

    private void createWord(){
        for(int i = 0; i < words.size();i++) {
            words.add(new Word());
        }
    }


    private void checkAnswer(){
        final RadioGroup radioGroup = findViewById(R.id.radioGroup);
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioButtonId);
        if(radioButtonId == R.id.radioButtonCorrect && isCorrect ){
            totalCorrect++;
        }
        else if(radioButtonId == R.id.radioButtonSkip){

        }
        else if(radioButtonId == R.id.radioButtonIncorrect && !isCorrect) {
            totalCorrect++;
        }
        else {
            gameOver();
        }
    }

    private void createPage(){
        TextView currentWordText = findViewById(R.id.textViewWord);
        end = findViewById(R.id.textViewEnd);
        words.add(new Word());
        current++;
        currentWord = words.get(current);
        currentWordText.setText(currentWord.returnWord());
        isCorrect = currentWord.returnBoolean();
        displayScore();

    }

    private void nextQuestion(){
        final Switch confirmSwitch = findViewById(R.id.switchConfirm);
        confirmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkAnswer();
                setTotalAnswered();
                createPage();
                confirmSwitch.setChecked(false);
            }
        });
    }

    private void setTotalAnswered(){
        if(totalAnswered < 9)
        totalAnswered++;
        else{
            gameOver();
        }
    }

    private void gameOver(){
        end.setBackgroundColor(Color.BLACK);
        if(totalCorrect == 10){
            end.setText("Perfect Score!");
        }
        else if(totalCorrect > 7){
            end.setText("Great Work");
        }
        else if(totalCorrect > 5){
            end.setText("Well you tried!");
        }
        else{
            end.setText("Ummm... laern 2 spel");
        }

    }

    private void startTimer(){
        time = findViewById(R.id.textViewTimer);
        runTimer();
    }

    public void updateSeconds() {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft % 60;
        String result = String.format("%02d:%02d", minutes, seconds);
        time.setText(result);
    }

    private void displayScore(){
        TextView score = findViewById(R.id.textViewScore);
        score.setText(totalCorrect+"/10");

    }

    public void runTimer(){
        new CountDownTimer(secondsLeft*1000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                secondsLeft--;
                updateSeconds();
            }

            @Override
            public void onFinish() {
                gameOver();
            }
        }.start();
    }

}
