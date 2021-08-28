package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // region declare
    TextView secondsTextView;
    TextView problemTextView;
    TextView scoreTextView;
    TextView resultTextView;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    Button playAgainButton;
    ConstraintLayout goLayout;
    ConstraintLayout gameLayout;
    CountDownTimer cTimer = null;

    int problemFirstNum;
    int problemSecondNum;
    int correctNum = 0;
    int attemptsNum = 0;
    int solution;
    // endregion declare

    public void startTimer() {
        cTimer = new CountDownTimer(30100, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {

                secondsTextView.setText(millisUntilFinished / 1000 + "s");

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {

                resultTextView.setText("Done!");
                textView1.setEnabled(false);
                textView2.setEnabled(false);
                textView3.setEnabled(false);
                textView4.setEnabled(false);
                playAgainButton.setVisibility(View.VISIBLE);

            }
        };
        cTimer.start();

    }

    @SuppressLint("SetTextI18n")
    public void gridClicked(View view) {

        attemptsNum += 1;

        TextView counter = (TextView) view;

        int selection = Integer.parseInt((String) counter.getText());

        resultTextView.setVisibility(View.VISIBLE);

        if (selection == solution) {

            correctNum += 1;
            resultTextView.setText("Correct!");

        } else {

            resultTextView.setText("Wrong :(");
        }

        scoreTextView.setText(correctNum + "/" + attemptsNum);

        reset();

    }

    @SuppressLint("SetTextI18n")
    public void reset() {

        int answer1Num;
        int answer2Num;
        int answer3Num;
        int answer4Num;
        int answerPosition = getRandomNumber(1, 4);

        problemFirstNum = getRandomNumber(0, 20);
        problemSecondNum = getRandomNumber(0, 20);

        solution = problemFirstNum + problemSecondNum;

        ArrayList<Integer> numbers = new ArrayList<>();
        while (numbers.size() <= 4) {

            int randomNum = getRandomNumber(0, 40);

            if (!numbers.contains(randomNum) && randomNum != solution) {

                numbers.add(randomNum);

            }
        }

        if (answerPosition == 1) {
            answer1Num = solution;
        } else {
            answer1Num = numbers.get(0);
        }
        textView1.setText(Integer.toString(answer1Num));

        if (answerPosition == 2) {
            answer2Num = solution;
        } else {
            answer2Num = numbers.get(1);
        }
        textView2.setText(Integer.toString(answer2Num));

        if (answerPosition == 3) {
            answer3Num = solution;
        } else {
            answer3Num = numbers.get(2);
        }
        textView3.setText(Integer.toString(answer3Num));


        if (answerPosition == 4) {
            answer4Num = solution;
        } else {
            answer4Num = numbers.get(3);
        }
        textView4.setText(Integer.toString(answer4Num));

        problemTextView.setText(problemFirstNum + " + " + problemSecondNum);

    }

    public void goClicked(View view) {

        goLayout.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);

        startTimer();

        reset();

    }

    @SuppressLint("SetTextI18n")
    public void playAgain(View view) {

        resultTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        correctNum = 0;
        attemptsNum = 0;

        scoreTextView.setText(correctNum + "/" + attemptsNum);

        textView1.setEnabled(true);
        textView2.setEnabled(true);
        textView3.setEnabled(true);
        textView4.setEnabled(true);

        startTimer();

        reset();

    }

    public int getRandomNumber(int min, int max) {

        return (new Random()).nextInt((max - min) + 1) + min;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region get
        secondsTextView = findViewById(R.id.secondsTextView);
        problemTextView = findViewById(R.id.problemTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        resultTextView = findViewById(R.id.resultTextView);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        playAgainButton = findViewById(R.id.playAgainButton);
        goLayout = findViewById(R.id.goLayout);
        gameLayout = findViewById(R.id.gameLayout);
        //endregion get

    }
}