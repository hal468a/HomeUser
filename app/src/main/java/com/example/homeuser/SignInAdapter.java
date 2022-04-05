package com.example.homeuser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SignInAdapter extends BaseAdapter {
    Context context;
    ArrayList<SignIn> arrayList;

    public SignInAdapter(Context context, ArrayList<SignIn> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.signin_item, null);
            TextView date_tv = (TextView)convertView.findViewById(R.id.date_tv);
            TextView status_tv = (TextView)convertView.findViewById(R.id.status_tv);

            SignIn signIn = arrayList.get(position);
            date_tv.setText(signIn.getDate());
            status_tv.setText(signIn.getStatus());

            return convertView;
    }
}
