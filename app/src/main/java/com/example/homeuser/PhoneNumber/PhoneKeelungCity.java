package com.example.homeuser.PhoneNumber;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.homeuser.R;

public class PhoneKeelungCity extends AppCompatActivity {

    private Button Phone_KeelungCity_btn1;
    private Button Phone_KeelungCity_btn2;
    private Button Phone_KeelungCity_btn3;
    private Button Phone_KeelungCity_btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_keelung_city);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("基隆市");
        actionBar.setDisplayShowHomeEnabled(true);  // back button
        actionBar.setDisplayHomeAsUpEnabled(true);

        Phone_KeelungCity_btn1 = (Button) findViewById(R.id.Phone_KeelungCity_btn1);
        Phone_KeelungCity_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone0224252119206();
            }
        });
        Phone_KeelungCity_btn2 = (Button) findViewById(R.id.Phone_KeelungCity_btn2);
        Phone_KeelungCity_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone0224252119207();
            }
        });
        Phone_KeelungCity_btn3 = (Button) findViewById(R.id.Phone_KeelungCity_btn3);
        Phone_KeelungCity_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone0224252119208();
            }
        });
        Phone_KeelungCity_btn4 = (Button) findViewById(R.id.Phone_KeelungCity_btn4);
        Phone_KeelungCity_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone1999();
            }
        });
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void callPhone0224252119206() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:0224252119,206");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhone0224252119207() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:0224252119,207");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhone0224252119208() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:0224252119,208");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhone1999() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:1999");
        intent.setData(data);
        startActivity(intent);
    }
}