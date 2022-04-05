package com.example.homeuser.PhoneNumber;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.homeuser.R;

public class PhoneNantouCounty extends AppCompatActivity {

    private Button Phone_NantouCounty_btn1;
    private Button Phone_NantouCounty_btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_nantou_county);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("南投縣");
        actionBar.setDisplayShowHomeEnabled(true);  // back button
        actionBar.setDisplayHomeAsUpEnabled(true);

        Phone_NantouCounty_btn1 = (Button) findViewById(R.id.Phone_NantouCounty_btn1);
        Phone_NantouCounty_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone0492220904();
            }
        });
        Phone_NantouCounty_btn2 = (Button) findViewById(R.id.Phone_NantouCounty_btn2);
        Phone_NantouCounty_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone0492246048();
            }
        });
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void callPhone0492220904() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:0492220904");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhone0492246048() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:0492246048");
        intent.setData(data);
        startActivity(intent);
    }
}