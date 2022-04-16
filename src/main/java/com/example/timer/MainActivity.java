package com.example.timer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public String name = "...";
    public String time = "00:00:00";
    public TextView text_title;
    public TextView text_clock;
    public ImageButton btn_start;
    public ImageButton btn_pause;
    public ImageButton btn_stop;
    public EditText edit_name;
    public LinearLayout linearLayout;

    boolean flag = false;
    boolean flag2 = false;
    boolean isCounting = false;
    public ClockThread thread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_title = findViewById(R.id.text_title);
        text_clock = findViewById(R.id.text_clock);
        btn_start = findViewById(R.id.btn_start);
        btn_pause = findViewById(R.id.btn_pause);
        btn_stop = findViewById(R.id.btn_stop);
        edit_name = findViewById(R.id.edit_name);
        linearLayout = findViewById(R.id.linearLayout);
        thread = new ClockThread();


        if(savedInstanceState!=null){
            int hour = 0;
            int minute = 0;
            int second = 0;
            String str1 = "";
            String str2 = "";
            String str3 = "";
            if(savedInstanceState.containsKey("hour")){
                hour = savedInstanceState.getInt("hour");
            }
            if(savedInstanceState.containsKey("minute")){
                minute = savedInstanceState.getInt("minute");
            }
            if(savedInstanceState.containsKey("second")){
                second = savedInstanceState.getInt("second");
            }
            if(savedInstanceState.containsKey("flag2")){
                flag2 = savedInstanceState.getBoolean("flag2");
            }
            ConstraintLayout.LayoutParams lp_btn = (ConstraintLayout.LayoutParams) linearLayout.getLayoutParams();
            ConstraintLayout.LayoutParams lp_text_title = (ConstraintLayout.LayoutParams) text_title.getLayoutParams();
            ConstraintLayout.LayoutParams lp_text_clock = (ConstraintLayout.LayoutParams) text_clock.getLayoutParams();
            if(flag2){
                Log.e("TAG", "1111111111111111111" );
                lp_btn.topMargin = 10;
                lp_text_title.topMargin=20;
                lp_text_clock.topMargin=10;
            }else{
                lp_btn.topMargin = 100;
                lp_text_title.topMargin=100;
                lp_text_clock.topMargin=180;
                Log.e("TAG", "222222222222222222" );
            }
            linearLayout.setLayoutParams(lp_btn);
            text_title.setLayoutParams(lp_text_title);
            text_clock.setLayoutParams(lp_text_clock);

            isCounting = savedInstanceState.getBoolean("isCounting");
            text_clock.setText(savedInstanceState.getString("time"));
            thread.hour = hour;
            thread.minute = minute;
            thread.second = second;
            if(isCounting){
                thread.start();
                flag = true;
            }

            //---

            if(second>=10){
                str1 = String.valueOf(second);
            }else{
                str1 = "0"+String.valueOf(second);
            }
            if(minute>=10){
                str2 = String.valueOf(minute);
            }else{
                str2 = "0"+String.valueOf(minute);
            }
            if(hour>=10){
                str3 = String.valueOf(hour);
            }else{
                str3 = "0"+String.valueOf(hour);
            }
            //---


            text_clock.setText(str3+":"+str2+":"+str1);
            flag = false;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        if(sharedPreferences!=null){
            name = sharedPreferences.getString("name","...");
            time = sharedPreferences.getString("time","00:00");
            text_title.setText("You spent "+time+" on "+name+" last time");
        }else{
            text_title.setText("You spent 00:00 on ... last time");
        }

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!flag){
                    thread.start();
                    flag = true;
                }else{
                    thread.isPause = false;
                }
                isCounting = true;
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread.isPause = true;
                isCounting = false;
            }

        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                if(thread.hour!=0){
                    editor.putString("time",thread.hourToString+":"+thread.minuteToString+":"+thread.secondToString);
                }else{
                    editor.putString("time",thread.minuteToString+":"+thread.secondToString);
                }

                String str = edit_name.getText().toString();
                if(str!=null){
                    editor.putString("name",str);
                }else{
                    editor.putString("name","...");
                }
                editor.apply();
                thread.isPause = true;
                isCounting = false;
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        flag2 = !flag2;
        Log.e("TAG", "onSaveInstanceState:    "+flag2 );
        outState.putBoolean("flag2",flag2);
        outState.putBoolean("isCounting",isCounting);
        outState.putBoolean("flag",true);
        if(thread.hour!=0){
            outState.putInt("hour",thread.hour);
            outState.putInt("minute",thread.minute);
            outState.putInt("second",thread.second);

        }else{
            outState.putInt("minute",thread.minute);
            outState.putInt("second",thread.second);
        }
        outState.putString("time",thread.hourToString+":"+thread.minuteToString+":"+thread.secondToString);

    }

    class ClockThread extends Thread{
        public int hour = 0;
        public int minute = 0;
        public int second = 0;
        public String hourToString = "00";
        public String minuteToString = "00";
        public String secondToString = "00";
        boolean isPause = false;

        @Override
        public void run() {
            super.run();
            while(true){
                while(isPause){}
                try {
                    sleep(1000);
                    second++;
                    if(second>=60){
                        minute++;
                        second=0;
                    }
                    if(minute>=60){
                        hour++;
                        minute=0;
                    }
                    if(hour>=24){
                        hour=0;
                    }
                    if(second>=10){
                        secondToString = String.valueOf(second);
                    }else{
                        secondToString = "0"+String.valueOf(second);
                    }
                    if(minute>=10){
                        minuteToString = String.valueOf(minute);
                    }else{
                        minuteToString = "0"+String.valueOf(minute);
                    }
                    if(hour>=10){
                        hourToString = String.valueOf(hour);
                    }else{
                        hourToString = "0"+String.valueOf(hour);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            text_clock.setText(hourToString+":"+minuteToString+":"+secondToString);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}