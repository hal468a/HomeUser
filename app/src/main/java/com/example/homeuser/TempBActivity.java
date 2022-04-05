package com.example.homeuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homeuser.sql.DbHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TempBActivity extends AppCompatActivity {

    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;
    private CheckBox checkBox7;
    private CheckBox checkBox8;
    private CheckBox checkBox9;
    private CheckBox checkBox10;
    private CheckBox checkBox11;
    private Button health_btn1;

    DbHelper dbHelper;
    Health health;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_b);

        dbHelper = new DbHelper(this);
        health = new Health();

        // set actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("健康問診");
        actionBar.setDisplayShowHomeEnabled(true);  // back button
        actionBar.setDisplayHomeAsUpEnabled(true);

        checkBox1 = (CheckBox) findViewById(R.id.health_cbox1);
        checkBox2 = (CheckBox) findViewById(R.id.health_cbox2);
        checkBox3 = (CheckBox) findViewById(R.id.health_cbox3);
        checkBox4 = (CheckBox) findViewById(R.id.health_cbox4);
        checkBox5 = (CheckBox) findViewById(R.id.health_cbox5);
        checkBox6 = (CheckBox) findViewById(R.id.health_cbox6);
        checkBox7 = (CheckBox) findViewById(R.id.health_cbox7);
        checkBox8 = (CheckBox) findViewById(R.id.health_cbox8);
        checkBox9 = (CheckBox) findViewById(R.id.health_cbox9);
        checkBox10 = (CheckBox) findViewById(R.id.health_cbox10);
        checkBox11 = (CheckBox) findViewById(R.id.health_cbox11);

        health_btn1 = (Button) findViewById(R.id.health_btn1);

        health_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeHealth();
                TempBActivity.this.finish();
            }
        });
    }//from activity to fragment

    private void takeHealth() {
        final Intent intent2 = getIntent();
        final String phone = intent2.getStringExtra("phone");

        CheckBox chk = null;
        int m = 0;
        int [] chb = {R.id.health_cbox1, R.id.health_cbox2, R.id.health_cbox3, R.id.health_cbox4, R.id.health_cbox5, R.id.health_cbox6, R.id.health_cbox7, R.id.health_cbox8, R.id.health_cbox9, R.id.health_cbox10};
        //判斷CheckBox是否是選中的
        for(int i:chb) {
            chk = (CheckBox)findViewById(i);
            if(chk.isChecked()) {
                m++;
            }
        }
        if(checkBox11.isChecked()){
            String check11 = checkBox11.getText().toString();
            int test11 = 1;

            for(int i:chb) {
                chk = (CheckBox)findViewById(i);
                chk.setChecked(false);
            }

            health.setK(test11);
            health.setHealthInfo(check11);


            Date now = new Date();
            long timestamp = now.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HH:mm:ss", Locale.TAIWAN);
            String date = sdf.format(timestamp);
            health.setDate(date);

            health.setUser_phone(phone);

            finish();

            dbHelper.addHealth(health);

            Toast.makeText(TempBActivity.this,"謝謝您的填寫！",Toast.LENGTH_LONG).show();

        } else if (m > 2){
            int test1 = 0;
            int test2 = 0;
            int test3 = 0;
            int test4 = 0;
            int test5 = 0;
            int test6 = 0;
            int test7 = 0;
            int test8 = 0;
            int test9 = 0;
            int test10 = 0;

            String checktext = "";
            for(int i:chb) {
                chk = (CheckBox) findViewById(i);
                if (chk.isChecked()) {
                    checktext = checktext + chk.getText().toString() + " ";
                    switch (chk.getText().toString()) {
                        case "發燒":
                            test1 = 1;
                            break;
                        case "肌肉痠痛":
                            test2 = 1;
                            break;
                        case "疲勞、全身無力":
                            test3 = 1;
                            break;
                        case "咳嗽":
                            test4 = 1;
                            break;
                        case "噁心":
                            test5 = 1;
                            break;
                        case "頭痛":
                            test6 = 1;
                            break;
                        case "腹痛、腹瀉":
                            test7 = 1;
                            break;
                        case "鼻塞、流鼻水":
                            test8 = 1;
                            break;
                        case "胸悶":
                            test9 = 1;
                            break;
                        case "呼吸困難":
                            test10 = 1;
                            break;
                    }
                }
            }
            health.setA(test1);
            health.setB(test2);
            health.setC(test3);
            health.setD(test4);
            health.setE(test5);
            health.setF(test6);
            health.setG(test7);
            health.setH(test8);
            health.setI(test9);
            health.setJ(test10);
            health.setHealthInfo(checktext);

            Date now = new Date();
            long timestamp = now.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HH:mm:ss", Locale.TAIWAN);
            String date = sdf.format(timestamp);
            health.setDate(date);

            health.setUser_phone(phone);

            Intent intent = new Intent();
            intent.setClass(TempBActivity.this, HomePhoneActivity.class);
            TempBActivity.this.startActivity(intent);

            dbHelper.addHealth(health);

            Toast.makeText(TempBActivity.this, "您的症狀需要回報，請撥打防疫專線通知！", Toast.LENGTH_LONG).show();

        } else if (m <= 2 && m > 0){
            int test1 = 0;
            int test2 = 0;
            int test3 = 0;
            int test4 = 0;
            int test5 = 0;
            int test6 = 0;
            int test7 = 0;
            int test8 = 0;
            int test9 = 0;
            int test10 = 0;
            String checktext = "";

            for(int i:chb) {
                chk = (CheckBox)findViewById(i);
                if(chk.isChecked()) {
                    checktext = checktext + chk.getText().toString() + " ";
                    switch (chk.getText().toString()) {
                        case "發燒":
                            test1 = 1;
                            break;
                        case "肌肉痠痛":
                            test2 = 1;
                            break;
                        case "疲勞、全身無力":
                            test3 = 1;
                            break;
                        case "咳嗽":
                            test4 = 1;
                            break;
                        case "噁心":
                            test5 = 1;
                            break;
                        case "頭痛":
                            test6 = 1;
                            break;
                        case "腹痛、腹瀉":
                            test7 = 1;
                            break;
                        case "鼻塞、流鼻水":
                            test8 = 1;
                            break;
                        case "胸悶":
                            test9 = 1;
                            break;
                        case "呼吸困難":
                            test10 = 1;
                            break;
                    }
                }
            }
            health.setA(test1);
            health.setB(test2);
            health.setC(test3);
            health.setD(test4);
            health.setE(test5);
            health.setF(test6);
            health.setG(test7);
            health.setH(test8);
            health.setI(test9);
            health.setJ(test10);
            health.setHealthInfo(checktext);

            Date now = new Date();
            long timestamp = now.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HH:mm:ss", Locale.TAIWAN);
            String date = sdf.format(timestamp);
            health.setDate(date);

            health.setUser_phone(phone);

            Toast.makeText(TempBActivity.this,"謝謝您的填寫！",Toast.LENGTH_LONG).show();
            dbHelper.addHealth(health);
            finish();
        } else if (checkBox11.isEnabled() && chk.isEnabled()){
            Toast.makeText(TempBActivity.this,"請確實填寫！",Toast.LENGTH_LONG).show();
        }
    }

    //back to the previous fragment
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
