package com.example.lostandfound;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    public TextView text_description;
    public TextView text_date;
    public TextView text_location;
    public Button btn_remove;
    public String u_id;
    private DatabaseHelper dbHelper;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));//设置状态栏颜色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏图标和文字颜色为暗色
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        text_description = findViewById(R.id.text_description);
        text_date = findViewById(R.id.text_date);
        text_location = findViewById(R.id.text_location);
        btn_remove = findViewById(R.id.btn_remove);
        dbHelper = new DatabaseHelper(this,"LocalDatabase.db",null,1);
        u_id = getIntent().getStringExtra("u_id");
        text_description.setText(getIntent().getStringExtra("description"));
        text_date.setText(getIntent().getStringExtra("date"));
        text_location.setText(getIntent().getStringExtra("location"));
        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Item","u_id=?",new String[]{u_id});
                Intent i = new Intent(DetailActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}