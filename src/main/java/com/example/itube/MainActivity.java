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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button btn_login;
    public Button btn_singUp;
    public EditText edit_username;
    public EditText edit_password;

    private DatabaseHelper dbHelper;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));//设置状态栏颜色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏图标和文字颜色为暗色
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        btn_login = findViewById(R.id.btn_login);
        btn_singUp = findViewById(R.id.btn_signUp);
        edit_username = findViewById(R.id.edit_username);
        edit_password = findViewById(R.id.edit_password);
        dbHelper = new DatabaseHelper(this,"LocalDatabase.db",null,3);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            @SuppressLint("Range")
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Person",null,"username=?", new String[]{edit_username.getText().toString()},
                        null,null,null);
                if(cursor.moveToFirst()){
                    String password =   cursor.getString(cursor.getColumnIndex("password"));
                    if(password.equals(edit_password.getText().toString())){
                        Intent i = new Intent(MainActivity.this,HomeActivity.class);
                        i.putExtra("username",edit_username.getText().toString());
                        startActivity(i);
                    }else{
                        Toast.makeText(MainActivity.this,"密码错误，请重新输入！",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else{
                    Toast.makeText(MainActivity.this,"用户不存在，请先完成注册！",Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });

        btn_singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });
    }
}