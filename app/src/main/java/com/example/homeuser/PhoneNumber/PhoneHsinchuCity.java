package com.example.homeuser.PhoneNumber;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.homeuser.R;

public class PhoneHsinchuCity extends AppCompatActivity {

    private Button Phone_HsinchuCity_btn1;
    private Button Phone_HsinchuCity_btn2;
    private Button Phone_HsinchuCity_btn3;
    private Button Phone_HsinchuCity_btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_hsinchu_city);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("新竹市");
        actionBar.setDisplayShowHomeEnabled(true);  // back button
        actionBar.setDisplayHomeAsUpEnabled(true);

        Phone_HsinchuCity_btn1 = (Button) findViewById(R.id.Phone_HsinchuCity_btn1);
        Phone_HsinchuCity_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone035266016();
            }
        });
        Phone_HsinchuCity_btn2 = (Button) findViewById(R.id.Phone_HsinchuCity_btn2);
        Phone_HsinchuCity_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone035216121304();
            }
        });
        Phone_HsinchuCity_btn3 = (Button) findViewById(R.id.Phone_HsinchuCity_btn3);
        Phone_HsinchuCity_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone035216121502();
            }
        });
        Phone_HsinchuCity_btn4 = (Button) findViewById(R.id.Phone_HualienCounty_btn4);
        Phone_HsinchuCity_btn4.setOnClickListener(new View.OnClickListener() {
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

    public void callPhone035266016() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:035266016");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhone035216121304() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:035216121,304");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhone035216121502() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:035216121,502");
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