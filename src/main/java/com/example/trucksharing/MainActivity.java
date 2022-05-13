package com.example.trucksharing;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button button_login_b;
    public Button button_sing_Up;
    public EditText edit_text_user_name;
    public EditText edit_text_pass_word;

    private DatabaseHelper Data_base_Helper_this;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ActionBar actionBar = getSupportActionBar();
        if(!(actionBar == null)){
            actionBar.hide();
        }
        button_login_b = findViewById(R.id.button_login_b);
        button_sing_Up = findViewById(R.id.btn_signUp);
        edit_text_user_name = findViewById(R.id.edit_text_user_name);
        edit_text_pass_word = findViewById(R.id.edit_text_pass_word);
        Data_base_Helper_this = new DatabaseHelper(this,"LocalDatabase.db",null,2);
        button_login_b.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            @SuppressLint("Range")
            public void onClick(View view) {
                SQLiteDatabase db = Data_base_Helper_this.getWritableDatabase();


                Cursor cursor = db.query("Person",null,"username=?", new String[]{edit_text_user_name.getText().toString()},
                        null,null,null);

                if(cursor.moveToFirst()){
                    String password =   cursor.getString(cursor.getColumnIndex("password"));
                    if(password.equals(edit_text_pass_word.getText().toString())){
                        Intent i = new Intent(MainActivity.this,HomeActivity.class);
                        i.putExtra("username",edit_text_user_name.getText().toString());
                        startActivity(i);
                    }else{
                        Toast.makeText(MainActivity.this,"password wrong",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else{
                    Toast.makeText(MainActivity.this,"user not exist,please register",Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });

        button_sing_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });
    }
}