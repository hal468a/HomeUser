package com.example.homeuser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TempRecordAdapter extends BaseAdapter {
    Context context;
    ArrayList<TempRecord> arrayList;

    public TempRecordAdapter(Context context, ArrayList<TempRecord> arrayList){
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
        convertView = inflater.inflate(R.layout.temp_record_item, null);
        TextView date_tv = (TextView)convertView.findViewById(R.id.date_tv);
        TextView time_tv = (TextView)convertView.findViewById(R.id.time_tv);
        TextView temp_tv = (TextView)convertView.findViewById(R.id.temp_tv);

        TempRecord tempRecord = arrayList.get(position);

        date_tv.setText(tempRecord.getDate());
        time_tv.setText(tempRecord.getTime());
        temp_tv.setText(tempRecord.getTemp());


        return convertView;
    }
}
