package com.example.quiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    public String name = "";
    public int count = 0;
    public TextView text_congratulation;
    public TextView text_score;
    public Button btn_newQuiz;
    public Button btn_close;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));//设置状态栏颜色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏图标和文字颜色为暗色
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        setContentView(R.layout.activity_result);
        SysApplication.getInstance().addActivity(this);
        name = getIntent().getStringExtra("name");
        count = getIntent().getIntExtra("count",0);

        text_congratulation = findViewById(R.id.text_congratulation);
        text_score = findViewById(R.id.text_score);
        btn_newQuiz = findViewById(R.id.btn_newQuiz);
        btn_close = findViewById(R.id.btn_close);
        text_congratulation.setText("Congratulation "+name+"!");
        text_score.setText(count+"/5");

        btn_newQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ResultActivity.this,MainActivity.class);
                i.putExtra("name",name);
                startActivity(i);
            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SysApplication.getInstance().exit();
            }
        });

    }
}