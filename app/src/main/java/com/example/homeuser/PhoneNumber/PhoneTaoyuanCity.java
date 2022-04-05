package com.example.homeuser.PhoneNumber;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.homeuser.R;

public class PhoneTaoyuanCity extends AppCompatActivity {

    private Button Phone_TaoyuanCity_btn1;
    private Button Phone_TaoyuanCity_btn2;
    private Button Phone_TaoyuanCity_btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_taoyuan_city);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("桃園市");
        actionBar.setDisplayShowHomeEnabled(true);  // back button
        actionBar.setDisplayHomeAsUpEnabled(true);

        Phone_TaoyuanCity_btn1 = (Button) findViewById(R.id.Phone_TaoyuanCity_btn1);
        Phone_TaoyuanCity_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone033335530();
            }
        });
        Phone_TaoyuanCity_btn2 = (Button) findViewById(R.id.Phone_TaoyuanCity_btn2);
        Phone_TaoyuanCity_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone033366730();
            }
        });
        Phone_TaoyuanCity_btn3 = (Button) findViewById(R.id.Phone_TaoyuanCity_btn3);
        Phone_TaoyuanCity_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone0800033355();
            }
        });
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void callPhone033335530() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:033335530");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhone033366730() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:033366730");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhone0800033355() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:0800033355");
        intent.setData(data);
        startActivity(intent);
    }
}