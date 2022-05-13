package com.example.lostandfound;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.UUID;

public class AddAdvertActivity extends AppCompatActivity{

    public EditText edit_name;
    public EditText edit_phone;
    public EditText edit_description;
    public EditText edit_date;
    public EditText edit_location;
    public Button btn_save;
    private DatabaseHelper dbHelper;

    public RadioGroup radioGroup;
    public RadioButton btn_lost;
    public RadioButton btn_found;
    public String type;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_advert);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));//设置状态栏颜色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏图标和文字颜色为暗色
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        edit_name = findViewById(R.id.edit_name);
        edit_phone = findViewById(R.id.edit_phone);
        edit_description = findViewById(R.id.edit_description);
        edit_date = findViewById(R.id.edit_date);
        edit_location = findViewById(R.id.edit_location);
        btn_save = findViewById(R.id.btn_save);
        radioGroup = findViewById(R.id.radioGroup);
        btn_lost = findViewById(R.id.btn_lost);
        btn_found = findViewById(R.id.btn_found);

        dbHelper = new DatabaseHelper(this,"LocalDatabase.db",null,1);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == btn_lost.getId()){
                    type = btn_lost.getText().toString();
                }
                if(i == btn_found.getId()){
                    type = btn_found.getText().toString();
                }
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                String u_id = UUID.randomUUID().toString();
                String name = edit_name.getText().toString();
                String phone = edit_phone.getText().toString();
                String description = edit_description.getText().toString();
                String date = edit_date.getText().toString();
                String location = edit_location.getText().toString();
                contentValues.put("u_id",u_id);
                contentValues.put("name",name);
                contentValues.put("phone",phone);
                contentValues.put("description",description);
                contentValues.put("date",date);
                contentValues.put("location",location);
                contentValues.put("type",type);
                db.insert("Item",null,contentValues);
                Toast.makeText(AddAdvertActivity.this,"Add successfully",Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }


}