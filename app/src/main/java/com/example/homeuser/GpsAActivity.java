package com.example.homeuser;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class GpsAActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_a);

        // set actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("居家定位");
        actionBar.setDisplayShowHomeEnabled(true);  // back button
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    //back to the previous fragment
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
