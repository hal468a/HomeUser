package com.example.homeuser;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


public class TempFragment extends Fragment {

    private BluetoothAdapter mBTAdapter;
    private ArrayAdapter<String> mBTArrayAdapter;
    private Button measuring_btn;
    private Button inquiry_btn;
    // used in bluetooth handler to identify message status
    private final static int REQUEST_ENABLE_BT = 1;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //action bar
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("健康檢測");

        View view = inflater.inflate(R.layout.fragment_temp, container, false);
        //set temp_btn1 and temp_btn2
        measuring_btn = (Button) view.findViewById(R.id.measuring_btn);
        inquiry_btn = (Button) view.findViewById(R.id.inquiry_btn);
        mBTArrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1);
        mBTAdapter = BluetoothAdapter.getDefaultAdapter();

        Intent intent = getActivity().getIntent();
        final String phone = intent.getStringExtra("phone");



        // 詢問藍芽裝置權限
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)!=  PackageManager.PERMISSION_GRANTED);


        if (mBTArrayAdapter == null) {
            // Device does not support Bluetooth
            Toast.makeText(getActivity().getApplicationContext(),"此裝置不支援藍芽功能",Toast.LENGTH_SHORT).show();
        } else {

            measuring_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mBTAdapter.isEnabled()){
                        toNextpage();
                        Toast.makeText(getActivity().getApplicationContext(),"選擇欲連線裝置", Toast.LENGTH_SHORT).show();
                    } else {
                        request_turnONBT();
                        Toast.makeText(getActivity().getApplicationContext(),"藍芽未開啟，請開啟後重試", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            inquiry_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2 = new Intent(getActivity(), TempBActivity.class);
                    intent2.putExtra("phone", phone);
                    startActivity(intent2);
                }
            });
        }
        return view;
    }

    private void request_turnONBT(){
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);    //跳出視窗
        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);                     //開啟設定藍芽畫面
    }

    private void toNextpage(){
        Intent intent = getActivity().getIntent();
        final String phone = intent.getStringExtra("phone");

        Intent intent1 = new Intent(getActivity(), TempAActivity.class);
        intent1.putExtra("phone", phone);
        startActivity(intent1);
    }
}