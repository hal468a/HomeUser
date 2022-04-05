package com.example.homeuser.PhoneNumber;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.homeuser.R;

public class PhoneHualienCounty extends AppCompatActivity {

    private Button Phone_HualienCounty_btn1;
    private Button Phone_HualienCounty_btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_hualien_county);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("花蓮縣");
        actionBar.setDisplayShowHomeEnabled(true);  // back button
        actionBar.setDisplayHomeAsUpEnabled(true);

        Phone_HualienCounty_btn1 = (Button) findViewById(R.id.Phone_HualienCounty_btn1);
        Phone_HualienCounty_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone038226975();
            }
        });
        Phone_HualienCounty_btn2 = (Button) findViewById(R.id.Phone_HualienCounty_btn2);
        Phone_HualienCounty_btn2.setOnClickListener(new View.OnClickListener() {
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

    public void callPhone1999() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:1999");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhone038226975() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:038226975");
        intent.setData(data);
        startActivity(intent);
    }
}