package com.example.homeuser;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.homeuser.sql.DbHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Register extends AppCompatActivity {

    private Button register_confirm_btn;
    private EditText rName, rPhone, rEmail, rPwd;
    private FirebaseAuth fAuth;
    private RadioGroup sexRadioGroup;
    private RadioButton boy, girl;

    private static final int REQUEST_CODE = 1;
    private DbHelper dbHelper;
    private long id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rName = (EditText) findViewById(R.id.rName);
        rPwd = (EditText) findViewById(R.id.rPwd);
        rPhone = (EditText) findViewById(R.id.rPhone);
        rEmail = (EditText) findViewById(R.id.rEmail);
        register_confirm_btn = (Button) findViewById(R.id.register_confirm_btn);

        sexRadioGroup = (RadioGroup) findViewById(R.id.sexRadioGroup);
        boy = (RadioButton) findViewById(R.id.boy);
        girl = (RadioButton) findViewById(R.id.girl);


        fAuth = FirebaseAuth.getInstance();

        dbHelper = new DbHelper(this);

        register_confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });

    }

    private void Register() {
        final String name = rName.getText().toString();
        final String phone = rPhone.getText().toString();
        final String password = rPwd.getText().toString();
        final String email = rEmail.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(Register.this, "請輸入姓名!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(Register.this, "請輸入信箱!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(Register.this, "請輸入手機!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(Register.this, "請輸入密碼!", Toast.LENGTH_SHORT).show();
        }else{
            ValidatePhoneNumber(name, phone, password, email);
        }
    }

    private void ValidatePhoneNumber(final String _name, final String _phone, final String _password, final String _emaill) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        final String name = rName.getText().toString();
        final String phone = rPhone.getText().toString();
        final String password = rPwd.getText().toString();
        final String email = rEmail.getText().toString();

        final String sex1 = boy.getText().toString();
        final String sex2 = girl.getText().toString();

        final String m = new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());

        Calendar c = Calendar.getInstance();
        final int day = c.get(Calendar.DAY_OF_MONTH);
        final String d = Integer.toString(day);

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!(dataSnapshot.child("HomeUsers").child(phone).exists())){

                    HashMap<String, Object> homedataMap = new HashMap<>();
                    homedataMap.put("name", name);
                    if (boy.isChecked()) {
                        homedataMap.put("sex", sex1);
                    } else if (girl.isChecked()) {
                        homedataMap.put("sex", sex2);
                    }
                    homedataMap.put("email", email);
                    homedataMap.put("password", password);
                    homedataMap.put("phone", phone);
                    homedataMap.put("register month", m);
                    homedataMap.put("register day", d);


                    RootRef.child("HomeUsers").child(phone).updateChildren(homedataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Users users = new Users();
                                        saveToSQLite(users);

                                        Toast.makeText(Register.this, "User created",Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(Register.this, LoginActivity.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(Register.this, "Error!",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else{
                    Toast.makeText(Register.this, "This" +  phone + "already exist.",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Register.this, LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void saveToSQLite(Users users) {
        users.setName(rName.getText().toString().trim());

        if (boy.isChecked()) {
            users.setSex(boy.getText().toString().trim());
        } else if (girl.isChecked()) {
            users.setSex(girl.getText().toString().trim());
        }

        users.setEmail(rEmail.getText().toString().trim());
        users.setPhone(rPhone.getText().toString().trim());
        users.setPassword(rPwd.getText().toString().trim());


        String m = new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());
//        final int month = Integer.parseInt(m);
        users.setRegister_date(m);

        dbHelper.addUser(users);
    }

}
