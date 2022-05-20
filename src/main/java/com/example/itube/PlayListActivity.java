package com.example.itube;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PlayListActivity extends AppCompatActivity  {


    public String username;
    public List<String> list;

    public ListView listView;

    private DatabaseHelper dbHelper;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));//设置状态栏颜色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏图标和文字颜色为暗色
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        listView = findViewById(R.id.lv_playlist);
        username = getIntent().getStringExtra("username");
        list = new ArrayList<>();
        dbHelper = new DatabaseHelper(this,"LocalDatabase.db",null,3);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("PlayList",null,"username=?", new String[]{username},
                null,null,null);
        if(cursor.moveToFirst()){
            do{
                String url =  cursor.getString(cursor.getColumnIndex("url"));
                list.add(url);
            }while(cursor.moveToNext());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(PlayListActivity.this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(PlayListActivity.this,PlayActivity.class);
                intent.putExtra("URL",list.get(i));
                startActivity(intent);
            }
        });



    }
}