package com.example.homeuser.PhoneNumber;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.homeuser.R;

public class PhoneKinmenCounty extends AppCompatActivity {

    private Button Phone_KinmenCounty_btn1;
    private Button Phone_KinmenCounty_btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_kinmen_county);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("金門縣");
        actionBar.setDisplayShowHomeEnabled(true);  // back button
        actionBar.setDisplayHomeAsUpEnabled(true);

        Phone_KinmenCounty_btn1 = (Button) findViewById(R.id.Phone_KinmenCounty_btn1);
        Phone_KinmenCounty_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone082330751();
            }
        });
        Phone_KinmenCounty_btn2 = (Button) findViewById(R.id.Phone_KinmenCounty_btn2);
        Phone_KinmenCounty_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone082330697();
            }
        });
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void callPhone082330751() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:082330751");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhone082330697() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:082330697");
        intent.setData(data);
        startActivity(intent);
    }
}