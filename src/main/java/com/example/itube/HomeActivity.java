package com.example.itube;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

public class HomeActivity extends AppCompatActivity {

    public EditText edit_url;
    public Button btn_play;
    public Button btn_addToList;
    public Button btn_playList;
    public String username;
    private DatabaseHelper dbHelper;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));//设置状态栏颜色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏图标和文字颜色为暗色
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        edit_url = findViewById(R.id.edit_url);
        btn_play = findViewById(R.id.btn_play);
        btn_addToList = findViewById(R.id.btn_addToList);
        btn_playList = findViewById(R.id.btn_playList);
        username = getIntent().getStringExtra("username");
        dbHelper = new DatabaseHelper(this,"LocalDatabase.db",null,3);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this,PlayActivity.class);
                i.putExtra("URL",edit_url.getText().toString());
                startActivity(i);
            }
        });

        btn_addToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("u_id", UUID.randomUUID().toString());
                contentValues.put("username",username);
                contentValues.put("url",edit_url.getText().toString());
                db.insert("PlayList",null,contentValues);
                Toast.makeText(HomeActivity.this, "Add successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btn_playList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,PlayListActivity.class);
                i.putExtra("username",username);
                startActivity(i);
            }
        });

    }
}