package com.example.homeuser;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.example.homeuser.sql.DbHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executor;

public class AccountSignInActivity extends AppCompatActivity {
    Button see_btn;
    Button sign_in_btn;
    DbHelper dbHelper;
    SignInRecord signInRecordEntity;

    final Handler mHandler = new Handler();
    final int delayMills = randBetween(100000, 200000);




    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_sign_in);

        // set actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("簽到");
        actionBar.setDisplayShowHomeEnabled(true);  // back button
        actionBar.setDisplayHomeAsUpEnabled(true);

        sign_in_btn = findViewById(R.id.sign_in_btn);


        dbHelper = new DbHelper(this);
        signInRecordEntity = new SignInRecord();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        try {
            setButtonVisible();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        see_btn = findViewById(R.id.see_btn);
        see_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(AccountSignInActivity.this, AccountSignInRecordActivity.class);
                startActivity(intent2);
            }
        });


        //create biometric manager and check if our user use the finger print or not
        BiometricManager biometricManager = BiometricManager.from(this);

        switch (biometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Toast.makeText(this, "您可以使用指紋或臉部辨識登入", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(this, "您的裝置無法使用指紋或臉部辨識登入", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(this, "您的裝置目前無法使用指紋或臉部辨識登入", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(this, "您的裝置目前沒有設定任何指紋或臉部辨識，請至設定中設置", Toast.LENGTH_SHORT).show();
                break;
        }

        //check if we are able to use biometric sensor or not
        //biometric dialog box

        //create an executor
        Executor executor = ContextCompat.getMainExecutor(this);

        //create the biometric prompt callback
        //this will give us the result of the authentication and if we can login or not
        final BiometricPrompt biometricPrompt = new BiometricPrompt(AccountSignInActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override //this method is call when  there is an error while the authentication
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override  //this method is call when the authentication is success
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                signInRecordInsertSQLite();
                Toast.makeText(getApplicationContext(), "簽到成功", Toast.LENGTH_SHORT).show();
            }

            @Override  //this method is call when we have failed the authentication
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        //create biometric dialog
        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("簽到")
                .setDescription("可以使用指紋或face id簽到")
                .setNegativeButtonText("cancel")
                .build();

        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                biometricPrompt.authenticate(promptInfo);
            }
        });

    }

    public void setButtonVisible() throws ParseException {
        String from = "00:00:00";
        String to = "05:00:00";


        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date_from = formatter.parse(from);
        Date date_to = formatter.parse(to);

        Calendar calendar = Calendar.getInstance();
        String currentTime = formatter.format(calendar.getTime());
        Date TimeNow = formatter.parse(currentTime);


        if (!(date_from.before(TimeNow) && date_to.after(TimeNow))) {
           startRepeating();
        }else{
            stopRepeating();
        }

    }

    private Runnable mToastRunnable = new Runnable() {
        @Override
        public void run() {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(AccountSignInActivity.this, "My Notification");
            builder.setContentTitle("一防萬疫 簽到通知");
            builder.setContentText("輪到您的簽到時間囉! 請點選至我的帳戶-簽到中進行簽到");
            builder.setSmallIcon(R.drawable.ic_home);
            builder.setAutoCancel(true);

            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(AccountSignInActivity.this);
            managerCompat.notify(1, builder.build());

            System.out.println(delayMills);

            ((Button) findViewById(R.id.sign_in_btn)).setVisibility(View.VISIBLE);
            mHandler.postDelayed(this, delayMills + 10000);

            if (sign_in_btn.getVisibility() == Button.VISIBLE) {
                ((Button) findViewById(R.id.sign_in_btn)).postDelayed(new Runnable() {
                    public void run() {
                        ((Button) findViewById(R.id.sign_in_btn)).setVisibility(View.INVISIBLE);


                    }
                }, delayMills );
            }
        }
    };

    public void startRepeating() {
        //mHandler.postDelayed(mToastRunnable, 5000);

        mToastRunnable.run();
    }
    public void stopRepeating() {
        mHandler.removeCallbacks(mToastRunnable);
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    public void signInRecordInsertSQLite(){
        Intent intent = getIntent();
        final String phone = intent.getStringExtra("phone");

        Date now = new Date();
        long timestamp = now.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HH:mm:ss", Locale.TAIWAN);
        String date = sdf.format(timestamp);

        signInRecordEntity.setDate(date);
        signInRecordEntity.setSignInStatus("已簽到");
        signInRecordEntity.setUser_phone(phone);
        dbHelper.addSignInRecord(signInRecordEntity);
    }

    //back to the previous fragment
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}