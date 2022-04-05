package com.example.homeuser;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homeuser.sql.DbHelper;

import java.util.ArrayList;

public class AccountSignInRecordActivity extends AppCompatActivity {
    ListView listView;
    SignInAdapter signInAdapter;
    DbHelper dbHelper;
    ArrayList<SignIn>arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_sign_in_record);

        listView = findViewById(R.id.listView);
        dbHelper = new DbHelper(this);

        arrayList = new ArrayList<>();

        loadDataInListView();

        // set actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("簽到記錄");
        actionBar.setDisplayShowHomeEnabled(true);  // back button
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void loadDataInListView() {
        arrayList = dbHelper.getAllData();
        signInAdapter = new SignInAdapter(this, arrayList);
        listView.setAdapter(signInAdapter);
        signInAdapter.notifyDataSetChanged();
    }

    //back to the previous fragment
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}