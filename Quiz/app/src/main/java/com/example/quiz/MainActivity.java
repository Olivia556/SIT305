package com.example.quiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public String name = "";
    public EditText textContent1;
    public Button button;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        ActionBar action = getSupportActionBar();
        if(!(action != null)) action.hide();
        setContentView(R.layout.activity_main);
        SysApplication.getInstance().addActivity(this);
        button = findViewById(R.id.btn_start);
        textContent1 = findViewById(R.id.textContent1);
        if(getIntent()!=null){
            name = getIntent().getStringExtra("name");
            textContent1.setText(name);
        }



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = textContent1.getText().toString();
                Intent i = new Intent(MainActivity.this,GameActivity.class);
                i.putExtra("name",name);
                startActivity(i);
            }
        });
    }


}