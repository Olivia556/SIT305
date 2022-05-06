package com.example.trucksharing;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.RequiresApi;
import java.util.UUID;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    public EditText edit_text_full_name_login;
    public EditText user_name_login_edit_text;
    public EditText edit_text_pass_word_login;
    public EditText edit_text_confirm_password_login;
    public EditText edit_text_phone_login;
    public Button button_create_account_login;
    private DatabaseHelper Data_base_Helper_this;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar == null){
            
        }else{
            actionBar.hide();
        }
        edit_text_full_name_login = findViewById(R.id.edit_text_full_name_login);
        user_name_login_edit_text = findViewById(R.id.user_name_login_edit_text);
        edit_text_pass_word_login = findViewById(R.id.edit_text_pass_word_login);
        edit_text_confirm_password_login = findViewById(R.id.edit_text_confirm_password_login);
        edit_text_phone_login = findViewById(R.id.edit_text_phone_login);
        button_create_account_login = findViewById(R.id.button_create_account_login);
        Data_base_Helper_this = new DatabaseHelper(this,"LocalDatabase.db",null,1);
        button_create_account_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !edit_text_pass_word_login.getText().toString().equals(edit_text_confirm_password_login.getText().toString())){
                    SQLiteDatabase db = Data_base_Helper_this.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("u_id", UUID.randomUUID().toString());
                    contentValues.put("fullName",edit_text_full_name_login.getText().toString());
                    contentValues.put("username",user_name_login_edit_text.getText().toString());
                    contentValues.put("password",edit_text_pass_word_login.getText().toString());
                    contentValues.put("phone",edit_text_phone_login.getText().toString());
                    db.insert("Person",null,contentValues);
                    Intent i = new Intent(SignUpActivity.this,MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(SignUpActivity.this,"The two passwords do not match",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

    }
}