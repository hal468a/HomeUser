package com.example.homeuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import com.example.homeuser.sql.DbHelper;

import java.util.ArrayList;

public class TempRecordActivity extends AppCompatActivity {
    ListView listView;
    TempRecord tempRecord;
    DbHelper dbHelper;
    ArrayList<TempRecord>arrayList;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_record);

        listView = findViewById(R.id.listView);
        dbHelper = new DbHelper(this);

        arrayList = new ArrayList<>();


        loadDataInListView();

        // set actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("額溫紀錄");
        actionBar.setDisplayShowHomeEnabled(true);  // back button
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void loadDataInListView() {
        arrayList = dbHelper.getAllTempData();
        TempRecordAdapter tempRecordAdapter= new TempRecordAdapter(this, arrayList);
        listView.setAdapter(tempRecordAdapter);
        tempRecordAdapter.notifyDataSetChanged();
    }
}