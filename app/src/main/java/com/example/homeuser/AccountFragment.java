package com.example.homeuser;

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

import com.example.homeuser.sql.DbHelper;

public class AccountFragment extends Fragment {

    TextView tv_name;
    DbHelper dbHelper;

    Users users;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //action bar
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("我的帳戶");

        View view = inflater.inflate(R.layout.fragment_account, container, false);
        return view;
    }

    @Override
    //按鈕設置
    public void onViewCreated(View view, Bundle savedInstanceState) {
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        dbHelper = new DbHelper(getContext());

        Intent intent = getActivity().getIntent();
        final String phone = intent.getStringExtra("phone");

        users = new Users();





        LinearLayout account_btn1=(LinearLayout) view.findViewById(R.id.account_btn1);
        account_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1= new Intent(getActivity(), AccountInfoActivity.class);
                intent1.putExtra("phone", phone);
                startActivity(intent1);
            }
        });

        LinearLayout account_btn2=(LinearLayout) view.findViewById(R.id.account_btn2);
        account_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2= new Intent(getActivity(), AccountTempActivity.class);
                intent2.putExtra("phone", phone);
                startActivity(intent2);
            }
        });

        LinearLayout account_btn3=(LinearLayout) view.findViewById(R.id.account_btn3);
        account_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3= new Intent(getActivity(), AccountHealthActivity.class);
                startActivity(intent3);
            }
        });


        LinearLayout account_btn5=(LinearLayout) view.findViewById(R.id.account_btn5);
        account_btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5= new Intent(getActivity(), AccountSignInActivity.class);
                startActivity(intent5);
            }
        });

        displayNameSQLite();
    }

    private void displayNameSQLite(){
        Intent intent = getActivity().getIntent();
        String email = intent.getStringExtra("phone");

        String name = dbHelper.getUserName(email);
        tv_name.setText(name);
    }
}
