package com.example.homeuser.PhoneNumber;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.homeuser.R;

public class PhoneYilanCounty extends AppCompatActivity {

    private Button Phone_YilanCounty_btn1;
    private Button Phone_YilanCounty_btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_yilan_county);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("宜蘭縣");
        actionBar.setDisplayShowHomeEnabled(true);  // back button
        actionBar.setDisplayHomeAsUpEnabled(true);

        Phone_YilanCounty_btn1 = (Button) findViewById(R.id.Phone_YilanCounty_btn1);
        Phone_YilanCounty_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone039357011();
            }
        });
        Phone_YilanCounty_btn2 = (Button) findViewById(R.id.Phone_YilanCounty_btn2);
        Phone_YilanCounty_btn2.setOnClickListener(new View.OnClickListener() {
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

    public void callPhone039357011() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:039357011");
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