package com.example.homeuser.sql;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.homeuser.HomeInfoActivity;
import com.example.homeuser.HomePhoneActivity;
import com.example.homeuser.R;
import com.example.homeuser.sql.DbHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeFragment extends Fragment {
    private TextView temp_daily, count;

    private DbHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //action bar
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("主頁");



        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }
    @Override
    //按鈕設置
    public void onViewCreated(View view, Bundle savedInstanceState) {
        temp_daily = (TextView)view.findViewById(R.id.temp_daily);
        count = (TextView)view.findViewById(R.id.count);

        dbHelper = new DbHelper(getActivity());

        if(findTime() >= 15){
            count.setText("結束");
        }else {
            count.setText("第" + findTime() + "天");
        }

        getTemp();

        LinearLayout home_phone_btn =(LinearLayout) view.findViewById(R.id.home_phone_btn);
        home_phone_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1= new Intent(getActivity(), HomePhoneActivity.class);
                startActivity(intent1);
            }
        });

        LinearLayout home_info_btn=(LinearLayout) view.findViewById(R.id.home_info_btn);
        home_info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2= new Intent(getActivity(), HomeInfoActivity.class);
                startActivity(intent2);
            }
        });
    }
    public void getTemp() {
        Intent intent = getActivity().getIntent();
        String phone = intent.getStringExtra("phone");



        String temp = dbHelper.getTemp(phone);

        if (temp.length() > 2) {
            temp_daily.setText(temp + " °C");
        }
    }

    private long findTime(){
        Intent intent = getActivity().getIntent();
        String phone = intent.getStringExtra("phone");

        String date = dbHelper.getUserRegisterDate(phone);

        long temp = 0;
        try{
            SimpleDateFormat sim = new SimpleDateFormat("yyyy/MM/dd");//定義日期時間格式，一定要進行ParseException的例外處理
            Date f = sim.parse(date);
            long firstmeet = f.getTime();//取得時間的unix時間
            Date now = new Date();//取得目前即時的時間
            long nowtime = now.getTime();//取得時間的unix時間
            temp = (nowtime-firstmeet)/(1000*60*60*24);
        }catch(ParseException e){

        }
        return temp;
    }
}
