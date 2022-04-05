package com.example.homeuser;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homeuser.Prevalent.Prevalent;
import com.example.homeuser.sql.DbHelper;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class
LoginActivity extends AppCompatActivity {
    private EditText lPhone, lPwd;
    private Button login_confirm_btn, register_btn;


    DbHelper dbHelper;
    Users users;

    private String parentDbName = "HomeUsers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        lPhone = (EditText)findViewById(R.id.lPhone);
        lPwd = (EditText)findViewById(R.id.lPwd);
        login_confirm_btn =  (Button) findViewById(R.id.login_confirm_btn);

        dbHelper = new DbHelper(this);
        users = new Users();

        register_btn = findViewById(R.id.register_btn);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, Register.class);
                startActivity(intent);
            }
        });
        login_confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });
    }

    private void LoginUser() {
        String phone = lPhone.getText().toString();
        String password = lPwd.getText().toString();

        if(TextUtils.isEmpty(phone)){
            Toast.makeText(LoginActivity.this, "請輸入手機!",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){

            Toast.makeText(LoginActivity.this, "請輸入密碼!",Toast.LENGTH_SHORT).show();
        }
        else{
            AllowAccessToAccount(phone, password);
        }
    }

    private void AllowAccessToAccount(final String phone, final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(parentDbName).child(phone).exists())
                {
                    Users usersData = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

                    if(usersData.getPhone().equals(phone)){
                        if(usersData.getPassword().equals(password)){
                            Toast.makeText(LoginActivity.this, "驗證碼已傳送!",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, PhoneOTPActivity.class);
                            Prevalent.currentonlineusers = usersData;
                            intent.putExtra("phone", phone);
                            getDataFromFirebase(phone);  //存入SQLite
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "帳號或密碼錯誤!!",Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                else{
                    Toast.makeText(LoginActivity.this, "手機不存在!",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void getDataFromFirebase(String phone) {

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

    public void saveToSQLite(String name, String sex, String email, String phone, String pwd, String reg_date) {
        users.setName(name);
        users.setSex(sex);
        users.setEmail(email);
        users.setPhone(phone);
        users.setPassword(pwd);
        users.setRegister_date(reg_date);
        dbHelper.addUser(users);
    }
}
