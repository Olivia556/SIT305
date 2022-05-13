package com.example.trucksharing;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Base64;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.io.ByteArrayOutputStream;

public class AccountActivity extends AppCompatActivity {

    public ImageView iv_headIcon;
    public TextView text_full_name_login;
    public TextView text_username_login;
    public TextView text_password_login;
    public TextView text_phone_login;
    public Button btn_back;
    private DatabaseHelper Data_base_Helper_this;
    public String username;
    @Override
    @SuppressLint("Range")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        iv_headIcon = findViewById(R.id.iv_headIcon);
        text_full_name_login = findViewById(R.id.text_full_name_login);
        text_username_login = findViewById(R.id.text_username_login);
        text_password_login = findViewById(R.id.text_password_login);
        text_phone_login = findViewById(R.id.text_phone_login);
        btn_back = findViewById(R.id.btn_back);
        username = getIntent().getStringExtra("username");
        Data_base_Helper_this = new DatabaseHelper(this,"LocalDatabase.db",null,2);
        SQLiteDatabase db = Data_base_Helper_this.getWritableDatabase();
        Cursor cursor = db.query("Person", null, "username=?", new String[]{username}, null, null, null);
        if(cursor.moveToFirst()){
            do{
                String u_id = cursor.getString(cursor.getColumnIndex("u_id"));
                String fullName = cursor.getString(cursor.getColumnIndex("fullName"));
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String password = cursor.getString(cursor.getColumnIndex("password"));
                String head = cursor.getString(cursor.getColumnIndex("head"));
                String phone = cursor.getString(cursor.getColumnIndex("phone"));
                text_full_name_login.setText("fullName : "+fullName);
                text_username_login.setText("username : "+username);
                text_password_login.setText("password:"+password);
                text_phone_login.setText("phone : "+phone);
                Bitmap bitmap = ImageUtil.base64ToImage(head);
                iv_headIcon.setImageBitmap(bitmap);
            }while(cursor.moveToNext());
        }
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}