package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    public int type = 0;
    public Spinner spinner;
    public EditText editText;
    public ImageButton button1;
    public ImageButton button2;
    public ImageButton button3;
    public TextView textView1;
    public TextView textView2;
    public TextView textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spin);
        button1 = findViewById(R.id.cm);
        button2 = findViewById(R.id.te);
        button3 = findViewById(R.id.we);
        editText = findViewById(R.id.content);
        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        textView3 = findViewById(R.id.text3);
        textView1.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);
        textView3.setVisibility(View.INVISIBLE);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if(type==0){
                    String str = editText.getText().toString();
                    Double value = Double.valueOf(str);
                    Double centimetre = getRound(value * 100);
                    Double foot = getRound(value * 3.2808);
                    Double inch = getRound(value * 39.3700);
                    textView1.setText(doubleToString(centimetre)+"  Centimetre");
                    textView2.setText(doubleToString(foot)+"  Foot");
                    textView3.setText(doubleToString(inch)+"  Inch");
                    textView1.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);

                    textView3.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(MainActivity.this, "Please select the correct conversion icon", 2000).show();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if(type==1){
                    String str = editText.getText().toString();
                    Double value = Double.valueOf(str);
                    Double fahrenheit = getRound((value * 9/5) + 32);
                    Double kelvin = getRound(value + 273.15);
                    textView1.setText(doubleToString(fahrenheit)+"  Fahrenheit");
                    textView2.setText(doubleToString(kelvin)+"  Kelvin");
                    textView1.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView3.setVisibility(View.INVISIBLE);
                }else{
                    Toast.makeText(MainActivity.this, "Please select the correct conversion icon", 2000).show();
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if(type==2){
                    String str = editText.getText().toString();
                    Double value = Double.valueOf(str);
                    Double grams = getRound(value * 1000);
                    Double ounce = getRound(value * 35.274);
                    Double pound = getRound(value * 2.2046);
                    textView1.setText(doubleToString(grams)+"  Gram");
                    textView2.setText(doubleToString(ounce)+"  Ounce(Oz)");
                    textView3.setText(doubleToString(pound)+"  Pound(lb)");
                    textView1.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView3.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(MainActivity.this, "Please select the correct conversion icon", 2000).show();
                }
            }
        });


    }
    double getRound(double a){
        int temp = (int)(a * 100 + 0.5);
        double result = temp/100.00;
        return result;
    }
    public static String doubleToString(double num){
        return new DecimalFormat("0.00").format(num);
    }
}