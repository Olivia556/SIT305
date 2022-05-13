package com.example.trucksharing;

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

import java.util.UUID;

public class AddOrderNextActivity extends AppCompatActivity {

    public String username = new String();
    public String time = new String();
    public String receiver = new String();
    public String goodType = new String();
    public String date = new String();
    public String location = new String();
    public String vehicleType = new String();

    public Button button_Order_create;
    public RadioGroup button_food_Type;
    public RadioGroup radio_vehicle_type;

    public RadioButton button_future;
    public RadioButton button_food_dry_type;
    public RadioButton btn_food;
    public RadioButton button_material_buildin;
    public RadioButton button_good_others;

    public RadioButton button_trucks;
    public RadioButton button_vans;
    public RadioButton button_re_truck;
    public RadioButton button_truck_mini;
    public RadioButton btn_other_vehicle;

    public EditText edit_weight_add_next;
    public EditText edit_width_add_next;
    public EditText edit_length_add_next;
    public EditText edit_height_add_next;

    public EditText edit_text_two;
    public EditText edit_text_three;

    private DatabaseHelper Data_base_Helper_this;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order_next);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ActionBar actionBar = getSupportActionBar();
        if(!(actionBar == null)){
            actionBar.hide();
        }
        username = getIntent().getStringExtra("username");
        receiver = getIntent().getStringExtra("receiver");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        location = getIntent().getStringExtra("location");


        button_Order_create = findViewById(R.id.button_Order_create);
        button_food_Type = findViewById(R.id.radioGroup);
        radio_vehicle_type = findViewById(R.id.radioGroup2);

        button_future = findViewById(R.id.button_future);
        button_food_dry_type = findViewById(R.id.button_food_dry_type);
        btn_food = findViewById(R.id.btn_food);
        button_material_buildin = findViewById(R.id.button_material_buildin);
        button_good_others = findViewById(R.id.button_good_others);

        button_trucks = findViewById(R.id.button_trucks);
        button_vans = findViewById(R.id.button_vans);
        button_re_truck = findViewById(R.id.button_re_truck);
        button_truck_mini = findViewById(R.id.button_truck_mini);
        btn_other_vehicle = findViewById(R.id.btn_other_vehicle);

        edit_weight_add_next = findViewById(R.id.edit_weight_add_next);
        edit_width_add_next = findViewById(R.id.edit_width_add_next);
        edit_length_add_next = findViewById(R.id.edit_length_add_next);
        edit_height_add_next = findViewById(R.id.edit_height_add_next);

        edit_text_two = findViewById(R.id.edit_text_two);
        edit_text_three = findViewById(R.id.edit_text_three);

        Data_base_Helper_this = new DatabaseHelper(this,"LocalDatabase.db",null,2);


        button_food_Type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == button_future.getId()){
                    goodType = button_future.getText().toString();
                }
                if(i == button_food_dry_type.getId()){
                    goodType = button_food_dry_type.getText().toString();
                }
                if(i == btn_food.getId()){
                    goodType = btn_food.getText().toString();
                }
                if(i == button_material_buildin.getId()){
                    goodType = button_material_buildin.getText().toString();
                }
                if(i == button_good_others.getId()){
                    goodType = edit_text_two.getText().toString();
                }

            }
        });

        radio_vehicle_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == button_trucks.getId()){
                    vehicleType = button_trucks.getText().toString();
                }
                if(i == button_vans.getId()){
                    vehicleType = button_vans.getText().toString();
                }
                if(i == button_re_truck.getId()){
                    vehicleType = button_re_truck.getText().toString();
                }
                if(i == button_truck_mini.getId()){
                    vehicleType = button_truck_mini.getText().toString();
                }
                if(i == btn_other_vehicle.getId()){
                    vehicleType = edit_text_three.getText().toString();
                }

            }
        });

        button_Order_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = Data_base_Helper_this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                String u_id = UUID.randomUUID().toString();
                contentValues.put("u_id",u_id);
                contentValues.put("sender",username);
                contentValues.put("receiver",receiver);
                contentValues.put("pickUpDate",date);
                contentValues.put("pickUpTime",time);
                contentValues.put("pickUpLocation",location);
                contentValues.put("goodType",goodType);
                contentValues.put("weight",edit_weight_add_next.getText().toString());
                contentValues.put("width",edit_width_add_next.getText().toString());
                contentValues.put("length",edit_length_add_next.getText().toString());
                contentValues.put("height",edit_height_add_next.getText().toString());
                contentValues.put("vehicleType",vehicleType);
                contentValues.put("isPublic","false");
                db.insert("OrderList",null,contentValues);
                Intent i = new Intent(AddOrderNextActivity.this,HomeActivity.class);
                i.putExtra("username",username);
                startActivity(i);

            }
        });
    }
}