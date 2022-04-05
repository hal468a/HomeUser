package com.example.homeuser;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homeuser.PhoneNumber.PhoneHsinchuCity;
import com.example.homeuser.PhoneNumber.PhoneHualienCounty;
import com.example.homeuser.PhoneNumber.PhoneKeelungCity;
import com.example.homeuser.PhoneNumber.PhoneKinmenCounty;
import com.example.homeuser.PhoneNumber.PhoneMiaoliCounty;
import com.example.homeuser.PhoneNumber.PhoneNantouCounty;
import com.example.homeuser.PhoneNumber.PhoneTaichungCity;
import com.example.homeuser.PhoneNumber.PhoneTaitungCounty;
import com.example.homeuser.PhoneNumber.PhoneTaoyuanCity;
import com.example.homeuser.PhoneNumber.PhoneYilanCounty;

public class HomePhoneActivity extends AppCompatActivity {

    private Button home_phone_btn1;
    private Button home_phone_btn2;
    private Button home_phone_btn3;
    private Button home_phone_btn4;
    private Button home_phone_btn5;
    private Button home_phone_btn6;
    private Button home_phone_btn7;
    private Button home_phone_btn8;
    private Button home_phone_btn9;
    private Button home_phone_btn10;
    private Button home_phone_btn11;
    private Button home_phone_btn12;
    private Button home_phone_btn13;
    private Button home_phone_btn14;
    private Button home_phone_btn15;
    private Button home_phone_btn16;
    private Button home_phone_btn17;
    private Button home_phone_btn18;
    private Button home_phone_btn19;
    private Button home_phone_btn20;
    private Button home_phone_btn21;
    private Button home_phone_btn22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_phone);

        // set actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("防疫專線");
        actionBar.setDisplayShowHomeEnabled(true);  // back button
        actionBar.setDisplayHomeAsUpEnabled(true);

        home_phone_btn1 = (Button) findViewById(R.id.home_phone_btn1);
        home_phone_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone1999();
            }
        });
        home_phone_btn2 = (Button) findViewById(R.id.home_phone_btn2);
        home_phone_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhoneNewTaipeiCity();
            }
        });
        home_phone_btn3 = (Button) findViewById(R.id.home_phone_btn3);
        home_phone_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeelungCity();
            }
        });
        home_phone_btn4 = (Button) findViewById(R.id.home_phone_btn4);
        home_phone_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openYilanCounty();
            }
        });
        home_phone_btn5 = (Button) findViewById(R.id.home_phone_btn5);
        home_phone_btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaoyuanCity();
            }
        });
        home_phone_btn6 = (Button) findViewById(R.id.home_phone_btn6);
        home_phone_btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhoneHsinchuCounty();
            }
        });
        home_phone_btn7 = (Button) findViewById(R.id.home_phone_btn7);
        home_phone_btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHsinchuCity();
            }
        });
        home_phone_btn8 = (Button) findViewById(R.id.home_phone_btn8);
        home_phone_btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMiaoliCounty();
            }
        });
        home_phone_btn9 = (Button) findViewById(R.id.home_phone_btn9);
        home_phone_btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaichungCity();
            }
        });
        home_phone_btn10 = (Button) findViewById(R.id.home_phone_btn10);
        home_phone_btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhoneChanghuaCounty();
            }
        });
        home_phone_btn11 = (Button) findViewById(R.id.home_phone_btn11);
        home_phone_btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNantouCounty();
            }
        });
        home_phone_btn12 = (Button) findViewById(R.id.home_phone_btn12);
        home_phone_btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhoneYunlinCounty();
            }
        });
        home_phone_btn13 = (Button) findViewById(R.id.home_phone_btn13);
        home_phone_btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhoneChiayiCounty();
            }
        });
        home_phone_btn14 = (Button) findViewById(R.id.home_phone_btn14);
        home_phone_btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhoneChiayiCity();
            }
        });
        home_phone_btn15 = (Button) findViewById(R.id.home_phone_btn15);
        home_phone_btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhoneTainanCity();
            }
        });
        home_phone_btn16 = (Button) findViewById(R.id.home_phone_btn16);
        home_phone_btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhoneKaohsiungCity();
            }
        });
        home_phone_btn17 = (Button) findViewById(R.id.home_phone_btn17);
        home_phone_btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhonePingtungCounty();
            }
        });
        home_phone_btn18 = (Button) findViewById(R.id.home_phone_btn18);
        home_phone_btn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone1999();
            }
        });
        home_phone_btn19 = (Button) findViewById(R.id.home_phone_btn19);
        home_phone_btn19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHualienCounty();
            }
        });
        home_phone_btn20 = (Button) findViewById(R.id.home_phone_btn20);
        home_phone_btn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaitungCounty();
            }
        });
        home_phone_btn21 = (Button) findViewById(R.id.home_phone_btn21);
        home_phone_btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone1999();
            }
        });
        home_phone_btn22 = (Button) findViewById(R.id.home_phone_btn22);
        home_phone_btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKinmenCounty();
            }
        });
    }

    //back to the previous fragment
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void openKeelungCity() {
        Intent intent = new Intent(this, PhoneKeelungCity.class);
        startActivity(intent);
    }

    public void openYilanCounty() {
        Intent intent = new Intent(this, PhoneYilanCounty.class);
        startActivity(intent);
    }

    public void openTaoyuanCity() {
        Intent intent = new Intent(this, PhoneTaoyuanCity.class);
        startActivity(intent);
    }

    public void openHsinchuCity() {
        Intent intent = new Intent(this, PhoneHsinchuCity.class);
        startActivity(intent);
    }

    public void openMiaoliCounty() {
        Intent intent = new Intent(this, PhoneMiaoliCounty.class);
        startActivity(intent);
    }

    public void openTaichungCity() {
        Intent intent = new Intent(this, PhoneTaichungCity.class);
        startActivity(intent);
    }

    public void openNantouCounty() {
        Intent intent = new Intent(this, PhoneNantouCounty.class);
        startActivity(intent);
    }

    public void openHualienCounty() {
        Intent intent = new Intent(this, PhoneHualienCounty.class);
        startActivity(intent);
    }

    public void openTaitungCounty() {
        Intent intent = new Intent(this, PhoneTaitungCounty.class);
        startActivity(intent);
    }

    public void openKinmenCounty() {
        Intent intent = new Intent(this, PhoneKinmenCounty.class);
        startActivity(intent);
    }

    public void callPhone1999() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:1999");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhoneNewTaipeiCity() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:0289535599,1520");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhoneHsinchuCounty() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:035511287");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhoneChanghuaCounty() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:047531366");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhoneYunlinCounty() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:055345811");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhoneChiayiCounty() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:053621150");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhoneChiayiCity() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:052338066");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhoneTainanCity() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:062880180");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhoneKaohsiungCity() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:078220300");
        intent.setData(data);
        startActivity(intent);
    }
    public void callPhonePingtungCounty() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:087326008");
        intent.setData(data);
        startActivity(intent);
    }
}
