package com.example.homeuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.homeuser.Gps.GpsBActivity;

public class GpsFragment extends Fragment {

    private Button gps_btn1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //action bar
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("居家定位");

        View view = inflater.inflate(R.layout.fragment_gps, container, false);

        Intent intent = getActivity().getIntent();
        final String phone = intent.getStringExtra("phone");


        //set gps_btn1 and gps_btn2
        gps_btn1 = (Button) view.findViewById(R.id.gps_btn1);

        gps_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getActivity(), GpsBActivity.class);
                intent2.putExtra("phone2", phone);
                startActivity(intent2);
            }
        });
        return view;
    }
}