package com.example.homeuser;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homeuser.sql.DbHelper;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.List;

public class AccountHealthActivity extends AppCompatActivity {

    private YAxis leftAxis;             //左侧Y轴
    private YAxis rightAxis;            //右侧Y轴
    private XAxis xAxis;                //X轴

    BarChart barChart;
    DbHelper dbHelper;
    SQLiteDatabase db;

    private String[] xDataL = {"發燒","肌肉痠痛","疲勞、全身無力","咳嗽","噁心","頭痛","腹痛、腹瀉","鼻塞、流鼻水","胸悶","呼吸困難"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_health);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("健康狀況分析");
        actionBar.setDisplayShowHomeEnabled(true);  // back button
        actionBar.setDisplayHomeAsUpEnabled(true);

        barChart = (BarChart) findViewById(R.id.barChart);
        dbHelper = new DbHelper(this);
        db = dbHelper.getWritableDatabase();


        List<BarEntry> dataValues = dbHelper.getData();

        BarDataSet barDataSet = new BarDataSet(dataValues,"Income By Income Category");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.75f);
        barData.getGroupWidth(0.06f,0.06f);

        //右下方description label：設置圖表資訊
        Description description = barChart.getDescription();
        description.setEnabled(true);//不顯示Description Label (預設顯示)
        description.setText("次數/症狀");
        description.setTextSize(16);
        description.setTextColor(Color.parseColor("#717171"));
        //左下方Legend：圖例數據資料
        Legend legend = barChart.getLegend();
        legend.setEnabled(false);//不顯示圖例 (預設顯示)
        barChart.setDrawGridBackground(false);//不顯示圖表網格
        barChart.getAxisRight().setEnabled(false);

        xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(-0.5f);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);//不繪製格網線

        xAxis.setValueFormatter(new IndexAxisValueFormatter(xDataL));
        xAxis.setLabelCount(xDataL.length);
        xAxis.setLabelRotationAngle(-40);

        leftAxis = barChart.getAxisLeft();
        rightAxis = barChart.getAxisRight();
        //保證Y軸從0開始，不然會上移
        leftAxis.setAxisMinimum(0f);
        //Y軸最大到14
        leftAxis.setAxisMaximum(15f);
        rightAxis.setAxisMinimum(0f);

        barChart.setScaleEnabled(true);
        // 設定是否可以用手指移動圖表
        barChart.setDragEnabled(true);

        Matrix matrix = new Matrix();
        // x軸放大4倍，y不變
        matrix.postScale(2.0f, 1.0f);
        // 設定縮放
        barChart.getViewPortHandler().refresh(matrix, barChart, false);

        barChart.setFitBars(false);
        barChart.clear();
        barChart.setData(barData);
        barChart.invalidate();

    }
    //back to the previous fragment
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}