package com.example.homeuser;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homeuser.sql.DbHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountInfoActivity extends AppCompatActivity {
    TextView tv_name, tv_sex, tv_email, tv_phone;
    EditText tv_pwd;
    Button logout_btn;

    DbHelper dbHelper;
    Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);

        tv_name = findViewById(R.id.name);
        tv_sex = findViewById(R.id.sex);
        tv_phone = findViewById(R.id.phone);
        tv_email = findViewById(R.id.email);
        tv_pwd = findViewById(R.id.pwd);
        logout_btn = findViewById(R.id.logout_btn);


        dbHelper = new DbHelper(this);
        users = new Users();

        // set actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("個人資訊");
        actionBar.setDisplayShowHomeEnabled(true);  // back button
        actionBar.setDisplayHomeAsUpEnabled(true);

        displayInfoFromSQLite();

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopLocationService();
                Intent signOutIntent = new Intent(AccountInfoActivity.this, LoginActivity.class);
                startActivity(signOutIntent);
            }
        });

    }

    //back to the previous fragment
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void getDataFromFirebase() {
        Intent intent2 = getIntent();
        final String phone = intent2.getStringExtra("phone");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("HomeUsers");
        ref.child(phone).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();
                String sex = dataSnapshot.child("sex").getValue().toString();
                String email = dataSnapshot.child("email").getValue().toString();
                String phone = dataSnapshot.child("phone").getValue().toString();
                String pwd = dataSnapshot.child("password").getValue().toString();
                String reg_date = dataSnapshot.child("register_date").getValue().toString();

                saveToSQLite(name, sex, email, phone, pwd, reg_date);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });


    }

    public void saveToSQLite(String name, String sex, String email, String phone, String pwd, String date) {
        users.setName(name);
        users.setSex(sex);
        users.setEmail(email);
        users.setPhone(phone);
        users.setPassword(pwd);
        users.setRegister_date(date);

        dbHelper.addUser(users);
    }

    public void displayInfoFromSQLite(){
        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");

        String name = dbHelper.getUserName(phone);
        tv_name.setText(name);

        String sex = dbHelper.getUserSex(phone);
        tv_sex.setText(sex);

        String email = dbHelper.getUserEmail(phone);
        tv_email.setText(email);

        tv_phone.setText(phone);

        String pwd = dbHelper.getUserPwd(phone);
        tv_pwd.setText(pwd);
    }

    private boolean isLocationServiceRunning(){
        ActivityManager activityManager =
                (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        if(activityManager != null){
            for(ActivityManager.RunningServiceInfo service :
                    activityManager.getRunningServices(Integer.MAX_VALUE)){
                if(LocationService.class.getName().equals(service.service.getClassName())){
                    if(service.foreground){
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    private void stopLocationService(){
        if(isLocationServiceRunning()){
            Intent intent = new Intent(getApplicationContext(),LocationService.class);
            intent.setAction(Constants.ACTION_STOP_LOCATION_SERVICE);
            stopService(intent);
            Toast.makeText(this,"Location service stopped.",Toast.LENGTH_SHORT).show();
        }
    }

}

