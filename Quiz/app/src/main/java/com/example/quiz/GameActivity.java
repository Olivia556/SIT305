package com.example.quiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    public TextView question1;
    public TextView question2;
    public TextView question3;
    public Button answer1;
    public Button answer2;
    public Button answer3;
    public String name = "";
    public int index = 1;
    public TextView firstT;
    public TextView secondT;
    public TextView titleT;
    public SeekBar bar_process;
    public Button submitButton;
    public int[] correct_answer = new int[]{1,2,2,3,1};
    public String[] questions_title = new String[]{"Android Fragmentation",
            "What is the purpose of HAL?",
            "The following is not part of the Service life cycle is",
            "The sent data type does not support",
            "About the function of broadcasting, the correct statement is"};
    public String[][] answers = new String[][]{{"refers to a massive number of different Android OS versions","refers to a massive number of same Android OS versions","refers to a small number of different Android OS versions"},
            {"To merge the Android framework with the Linux kernel","To separate the Android framework from the Linux kernel","To relying on the Linux kernel for Android"},
            {"onCreate","onStop","onDestroy"},
            {"Boolean","String","double"},
            {"It can start an activity","It cannot start a service","It can help service modify the user interface"}};
    public int numberFlag = 0;

    public int chooseFlag = 0;
    public boolean flag = false;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        setContentView(R.layout.activity_game);
        SysApplication.getInstance().addActivity(this);
        firstT = findViewById(R.id.firstT);
        secondT = findViewById(R.id.secondT);
        question1 = findViewById(R.id.question1);
        question2 = findViewById(R.id.question2);
        question3 = findViewById(R.id.question3);
        titleT = findViewById(R.id.titleT);
        bar_process = findViewById(R.id.bar_process);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        submitButton = findViewById(R.id.submitButton);

        name = getIntent().getStringExtra("name");
        firstT.setText("Welcome "+name+"!");
        bar_process.setMax(100);
        nextQuestion();

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                    return;
                }
                answer1.setBackgroundResource(R.drawable.btn_select_shape);
                answer2.setBackgroundResource(R.drawable.answerbtn_shape);
                answer3.setBackgroundResource(R.drawable.answerbtn_shape);
                chooseFlag = 1;
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                    return;
                }
                answer1.setBackgroundResource(R.drawable.answerbtn_shape);
                answer2.setBackgroundResource(R.drawable.btn_select_shape);
                answer3.setBackgroundResource(R.drawable.answerbtn_shape);
                chooseFlag = 2;
            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                    return;
                }
                answer1.setBackgroundResource(R.drawable.answerbtn_shape);
                answer2.setBackgroundResource(R.drawable.answerbtn_shape);
                answer3.setBackgroundResource(R.drawable.btn_select_shape);
                chooseFlag = 3;
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                    flag = false;
                    submitButton.setText("Submit");
                    index++;
                    if(index<=5){
                        answer1.setBackgroundResource(R.drawable.answerbtn_shape);
                        answer2.setBackgroundResource(R.drawable.answerbtn_shape);
                        answer3.setBackgroundResource(R.drawable.answerbtn_shape);
                    }else{
                        Intent i = new Intent(GameActivity.this,ResultActivity.class);
                        i.putExtra("name",name);
                        i.putExtra("count",numberFlag);
                        startActivity(i);
                    }

                    nextQuestion();
                }else{
                    if(chooseFlag==0){
                        Toast.makeText(GameActivity.this,"please choose an answer",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    flag = true;
                    submitButton.setText("Next");
                    refreshAfterchooseFlag();
                }

            }
        });
    }

    public void nextQuestion() {
        if(index>5){
            return;
        }
        secondT.setText(index + "/5");
        bar_process.setProgress(index*20);
        titleT.setText(index+"、"+questions_title[index-1]);
        question1.setText("A、"+answers[index-1][0]);
        question2.setText("B、"+answers[index-1][1]);
        question3.setText("C、"+answers[index-1][2]);
        chooseFlag = 0;


    }

    public void refreshAfterchooseFlag(){
        if(chooseFlag== correct_answer[index-1]){

            numberFlag++;
            switch (chooseFlag){
                case 1:
                    answer1.setBackgroundResource(R.drawable.btn_true_shape);
                    answer2.setBackgroundResource(R.drawable.answerbtn_shape);
                    answer3.setBackgroundResource(R.drawable.answerbtn_shape);
                    break;
                case 2:
                    answer1.setBackgroundResource(R.drawable.answerbtn_shape);
                    answer2.setBackgroundResource(R.drawable.btn_true_shape);
                    answer3.setBackgroundResource(R.drawable.answerbtn_shape);
                    break;
                case 3:
                    answer1.setBackgroundResource(R.drawable.answerbtn_shape);
                    answer2.setBackgroundResource(R.drawable.answerbtn_shape);
                    answer3.setBackgroundResource(R.drawable.btn_true_shape);
                    break;
                default:
                    break;
            }
        }else{
            answer1.setBackgroundResource(R.drawable.answerbtn_shape);
            answer2.setBackgroundResource(R.drawable.answerbtn_shape);
            answer3.setBackgroundResource(R.drawable.answerbtn_shape);
            switch (chooseFlag){
                case 1:
                    answer1.setBackgroundResource(R.drawable.btn_false_shape);
                    break;
                case 2:
                    answer2.setBackgroundResource(R.drawable.btn_false_shape);
                    break;
                case 3:
                    answer3.setBackgroundResource(R.drawable.btn_false_shape);
                    break;
                default:
                    break;
            }
            switch (correct_answer[index-1]){
                case 1:
                    answer1.setBackgroundResource(R.drawable.btn_true_shape);
                    break;
                case 2:
                    answer2.setBackgroundResource(R.drawable.btn_true_shape);
                    break;
                case 3:
                    answer3.setBackgroundResource(R.drawable.btn_true_shape);
                    break;
                default:
                    break;
            }
        }
    }


}