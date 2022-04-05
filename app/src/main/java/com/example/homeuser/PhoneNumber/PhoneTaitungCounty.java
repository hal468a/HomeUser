package com.example.homeuser.PhoneNumber;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.homeuser.R;

public class PhoneTaitungCounty extends AppCompatActivity {

    private Button Phone_TaitungCounty_btn1;
    private Button Phone_TaitungCounty_btn2;
    private Button Phone_TaitungCounty_btn3;
    private Button Phone_TaitungCounty_btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_taitung_county);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("台東縣");
        actionBar.setDisplayShowHomeEnabled(true);  // back button
        actionBar.setDisplayHomeAsUpEnabled(true);

        Phone_TaitungCounty_btn1 = (Button) findViewById(R.id.Phone_TaitungCounty_btn1);
        Phone_TaitungCounty_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone089352674214();
            }
        });
        Phone_TaitungCounty_btn2 = (Button) findViewById(R.id.Phone_TaitungCounty_btn2);
        Phone_TaitungCounty_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone089331171217();
            }
        });
        Phone_TaitungCounty_btn3 = (Button) findViewById(R.id.Phone_TaitungCounty_btn3);
        Phone_TaitungCounty_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone0900739538();
            }
        });
        Phone_TaitungCounty_btn4 = (Button) findViewById(R.id.Phone_TaitungCounty_btn4);
        Phone_TaitungCounty_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone0975288155();
            }
        });
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void callPhone089352674214() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:089352674,214");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhone089331171217() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:089331171,217");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhone0900739538() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:0900739538");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhone0975288155() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:0975288155");
        intent.setData(data);
        startActivity(intent);
    }
}