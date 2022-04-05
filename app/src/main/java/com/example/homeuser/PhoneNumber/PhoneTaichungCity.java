package com.example.homeuser.PhoneNumber;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.homeuser.R;

public class PhoneTaichungCity extends AppCompatActivity {

    private Button Phone_TaichungCity_btn1;
    private Button Phone_TaichungCity_btn2;
    private Button Phone_TaichungCity_btn3;
    private Button Phone_TaichungCity_btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_taichung_city);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("台中市");
        actionBar.setDisplayShowHomeEnabled(true);  // back button
        actionBar.setDisplayHomeAsUpEnabled(true);

        Phone_TaichungCity_btn1 = (Button) findViewById(R.id.Phone_TaichungCity_btn1);
        Phone_TaichungCity_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone042228911121696();
            }
        });
        Phone_TaichungCity_btn2 = (Button) findViewById(R.id.Phone_TaichungCity_btn2);
        Phone_TaichungCity_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone042228911121697();
            }
        });
        Phone_TaichungCity_btn3 = (Button) findViewById(R.id.Phone_TaichungCity_btn3);
        Phone_TaichungCity_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone042228911121698();
            }
        });
        Phone_TaichungCity_btn4 = (Button) findViewById(R.id.Phone_TaichungCity_btn4);
        Phone_TaichungCity_btn4.setOnClickListener(new View.OnClickListener() {
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

    public void callPhone042228911121696() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:0422289111,21696");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhone042228911121697() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:0422289111,21697");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhone042228911121698() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:0422289111,21698");
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