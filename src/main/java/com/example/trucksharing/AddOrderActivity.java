package com.example.trucksharing;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddOrderActivity extends AppCompatActivity {

    public String username;
    public Button next_button_put;
    public EditText user_name_add;
    public EditText user_date_add;
    public EditText user_time_add;
    public EditText user_location_add;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        next_button_put = findViewById(R.id.next_button_put);
        user_name_add = findViewById(R.id.user_name_add);
        user_date_add = findViewById(R.id.user_date_add);
        user_time_add = findViewById(R.id.user_time_add);
        user_location_add = findViewById(R.id.user_location_add);
        username = getIntent().getStringExtra("username");

        next_button_put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddOrderActivity.this,AddOrderNextActivity.class);
                i.putExtra("username",username);
                i.putExtra("receiver",user_name_add.getText().toString());
                i.putExtra("date",user_date_add.getText().toString());
                i.putExtra("time",user_time_add.getText().toString());
                i.putExtra("location",user_location_add.getText().toString());
                startActivity(i);
            }
        });

    }

}