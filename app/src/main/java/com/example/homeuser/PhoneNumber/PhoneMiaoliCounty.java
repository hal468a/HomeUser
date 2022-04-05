package com.example.homeuser.PhoneNumber;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.homeuser.R;

public class PhoneMiaoliCounty extends AppCompatActivity {

    private Button Phone_MiaoliCounty_bt1;
    private Button Phone_MiaoliCounty_bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_miaoli_county);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("苗栗縣");
        actionBar.setDisplayShowHomeEnabled(true);  // back button
        actionBar.setDisplayHomeAsUpEnabled(true);

        Phone_MiaoliCounty_bt1 = (Button) findViewById(R.id.Phone_MiaoliCounty_bt1);
        Phone_MiaoliCounty_bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone037559520();
            }
        });
        Phone_MiaoliCounty_bt2 = (Button) findViewById(R.id.Phone_MiaoliCounty_bt2);
        Phone_MiaoliCounty_bt2.setOnClickListener(new View.OnClickListener() {
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

    public void callPhone037559520() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:037559520");
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