package com.example.itube;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class SignUpActivity extends AppCompatActivity {

    public EditText edit_full_name_login;
    public EditText edit_username_login;
    public EditText edit_password_login;
    public EditText edit_confirm_password_login;
    public Button btn_create_account_login;


    private DatabaseHelper dbHelper;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));//设置状态栏颜色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏图标和文字颜色为暗色
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }



        edit_full_name_login = findViewById(R.id.edit_full_name_login);
        edit_username_login = findViewById(R.id.edit_username_login);
        edit_password_login = findViewById(R.id.edit_password_login);
        edit_confirm_password_login = findViewById(R.id.edit_confirm_password_login);
        btn_create_account_login = findViewById(R.id.btn_create_account_login);


        dbHelper = new DatabaseHelper(this,"LocalDatabase.db",null,3);

        btn_create_account_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edit_password_login.getText().toString().equals(edit_confirm_password_login.getText().toString())){
                    Toast.makeText(SignUpActivity.this,"输入的两次密码不一致！",Toast.LENGTH_SHORT).show();
                    return;
                }
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("u_id", UUID.randomUUID().toString());
                contentValues.put("fullName",edit_full_name_login.getText().toString());
                contentValues.put("username",edit_username_login.getText().toString());
                contentValues.put("password",edit_password_login.getText().toString());
                db.insert("Person",null,contentValues);
                Intent i = new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }




}