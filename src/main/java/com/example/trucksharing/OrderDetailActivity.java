package com.example.trucksharing;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class OrderDetailActivity extends AppCompatActivity {
    public TextView text_sender_detail;
    public TextView text_pick_up_time_detail;
    public TextView text_receiver_detail;
    public TextView text_weight;
    public TextView text_good_type;
    public TextView text_width;
    public TextView text_height;
    public TextView text_length;
    public Button btn_call_driver;
    public String sender=new String();
    public String receiver=new String();
    public String pickUpTime=new String();
    public String goodType=new String();
    public String weight = new String();
    public String width = new String();
    public String height = new String();
    public String Length = new String();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        sender = getIntent().getStringExtra("sender");
        weight = getIntent().getStringExtra("weight");
        width = getIntent().getStringExtra("width");
        height = getIntent().getStringExtra("height");
        Length = getIntent().getStringExtra("Length");
        receiver = getIntent().getStringExtra("receiver");
        pickUpTime = getIntent().getStringExtra("pickUpTime");
        goodType = getIntent().getStringExtra("goodType");
        text_weight = findViewById(R.id.text_weight);
        text_good_type = findViewById(R.id.text_good_type);
        text_width = findViewById(R.id.text_width);
        text_height = findViewById(R.id.text_height);
        text_length = findViewById(R.id.text_length);
        text_sender_detail = findViewById(R.id.text_sender_detail);
        text_pick_up_time_detail = findViewById(R.id.text_pick_up_time_detail);
        text_receiver_detail = findViewById(R.id.text_receiver_detail);
        btn_call_driver = findViewById(R.id.btn_call_driver);
        text_receiver_detail.setText("To Receiver:"+receiver);
        text_weight.setText("Weight:"+weight);
        text_good_type.setText("Good Type:"+goodType);
        text_pick_up_time_detail.setText("Pick up time:"+pickUpTime);
        text_width.setText("Width:"+width);
        text_height.setText("Height:"+height);
        text_length.setText("Length:"+Length);
        text_sender_detail.setText("From Sender:"+sender);
        btn_call_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderDetailActivity.this,HomeActivity.class);
                intent.putExtra("username",sender);
                startActivity(intent);
            }
        });

    }
}