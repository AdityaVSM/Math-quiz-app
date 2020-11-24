package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    Button goButton;
    Button playAgainButton;
    TextView calcTextView;
    ConstraintLayout gameLayout;
    Button b0;
    Button b1;
    Button b2;
    Button b3;
    ArrayList<Integer> resultList = new ArrayList<Integer>();
    int correctAnswerPosition;
    TextView resultTextView;
    int score = 0;
    int numberOfQuestions = 0;
    TextView scoreTextView;
    TextView timerTextView;

    public void chooseAnswer(View view){
        if(Integer.toString(correctAnswerPosition).equals(view.getTag().toString())) {
            resultTextView.setText("Correct:)");
            score++;
        }
        else
            resultTextView.setText("Wrong answer:(");
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        setNewQuestion();
    }

    public void start(View view){
        goButton.setVisibility(view.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(scoreTextView);
    }

    public void setNewQuestion(){
        Random r = new Random();
        int a = r.nextInt(40);
        int b = r.nextInt(40);
        calcTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));
        correctAnswerPosition = r.nextInt(4);
        resultList.clear();
        for(int i=0;i<4;i++){
            if(i == correctAnswerPosition)
                resultList.add(a+b);
            else{
                int wrongAnswer = r.nextInt(100);
                while(wrongAnswer == a+b)
                    wrongAnswer = r.nextInt(100);
                resultList.add(wrongAnswer);
            }
        }
        b0.setText(Integer.toString(resultList.get(0)));
        b1.setText(Integer.toString(resultList.get(1)));
        b2.setText(Integer.toString(resultList.get(2)));
        b3.setText(Integer.toString(resultList.get(3)));
    }

    public void playAgain(View view){
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));

        setNewQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("DONE!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameLayout = findViewById(R.id.gameLayout);
        goButton = findViewById(R.id.goButton);
        calcTextView = (TextView)findViewById(R.id.calcTextView);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);

        b0 = findViewById(R.id.button0);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}