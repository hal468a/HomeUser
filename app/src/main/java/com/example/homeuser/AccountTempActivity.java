package com.example.homeuser;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homeuser.sql.DbHelper;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

public class AccountTempActivity extends AppCompatActivity {

    Button record;

    LineChart lineChart;
    LineDataSet lineDataSet = new LineDataSet(null, null);
    ArrayList<ILineDataSet> dataSets = new ArrayList<>();
    LineData lineData;
    DbHelper dbHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("額溫紀錄");
        actionBar.setDisplayShowHomeEnabled(true);  // back button
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_account_temp);
        lineChart = findViewById(R.id.lineChart);
        record = findViewById(R.id.record);

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(AccountTempActivity.this, TempRecordActivity.class);
                startActivity(intent2);
            }
        });


        dbHelper = new DbHelper(this);
        db = dbHelper.getWritableDatabase();

        Intent intent = getIntent();
        final String phone = intent.getStringExtra("phone");


        final LineDataSet set, set1;
        // greenLine
        set = new LineDataSet(null, null);
        set.setValues(getData1(phone));
        set.setMode(LineDataSet.Mode.LINEAR);//類型為折線
        set.setColor(getResources().getColor(R.color.colorPrimary));//線的顏色
        set.setLineWidth(4);//線寬
        set.setCircleColor(Color.parseColor("#35A58D"));
        set.setCircleRadius(4);
        set.setDrawCircles(true); //不顯示相應座標點的小圓圈(預設顯示)
        set.setDrawValues(true);//不顯示座標點對應Y軸的數字(預設顯示)

        // yellowLine
        set1 = new LineDataSet(null, null);
        set1.setValues(getData2(phone));
        set1.setMode(LineDataSet.Mode.LINEAR);//類型為折線
        set1.setColor(getResources().getColor(R.color.colorRed));//線的顏色
        set1.setLineWidth(4);//線寬
        set1.setCircleRadius(4);
        set1.setCircleColor(Color.parseColor("#A60101"));
        set1.setDrawCircles(true); //顯示相應座標點的小圓圈(預設顯示)
        set1.setDrawValues(true);//不顯示座標點對應Y軸的數字(預設顯示)

        //理解爲多條線的集合
        LineData data = new LineData(set, set1);
        lineChart.setData(data);//一定要放在最後
        lineChart.invalidate();//繪製圖表

        Matrix matrix = new Matrix();
        // x軸放大4倍，y不變
        matrix.postScale(2.0f, 1.0f);
        lineChart.getViewPortHandler().refresh(matrix, lineChart, false);


        try {
            initX();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        initY();
        initChartFormat();
    }

    //back to the previous fragment
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private ArrayList<Entry> getData1(String phone){
        db = dbHelper.getReadableDatabase();
        float xValues = 0;
        ArrayList<Entry> dataValues = new ArrayList<>();

        String selectQuery = "SELECT `temp` FROM temperature WHERE user_phone = ? GROUP BY date HAVING MIN(temp_id) ORDER BY temp_id";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{phone});//sorting
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToNext();
            xValues = xValues + 1 ;
            dataValues.add(new Entry(xValues, cursor.getFloat(0)));
        }

        return dataValues;
    }

    private ArrayList<Entry> getData2(String phone){
        db = dbHelper.getReadableDatabase();
        float xValues = 0;
        ArrayList<Entry> dataValues = new ArrayList<>();

        String selectQuery = "SELECT `temp` FROM temperature WHERE user_phone = ? GROUP BY date HAVING MAX(temp_id) ORDER BY temp_id";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{phone});//sorting
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToNext();
            xValues = xValues + 1 ;
            dataValues.add(new Entry(xValues, cursor.getFloat(0)));
        }
        return dataValues;
    }

    private void initX() throws ParseException {
        XAxis xAxis = lineChart.getXAxis();
        //設定所需特定標籤資料
        final String[] date = {"", "第0天", "第1天", "第2天", "第3天", "第4天", "第5天", "第6天", "第7天", "第8天", "第9天", "第10天", "第11天", "第12天", "第13天", "第14天"}; // Your List / array with String Values For X-axis Labels
        xAxis.setValueFormatter(new IndexAxisValueFormatter(date));

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//X軸標籤顯示位置(預設顯示在上方，分為上方內/外側、下方內/外側及上下同時顯示)
        xAxis.setTextColor(Color.BLACK);//X軸標籤顏色
        xAxis.setTextSize(12);//X軸標籤大小

        xAxis.setLabelCount(date.length);//X軸標籤個數
        xAxis.setSpaceMin(0.5f);//折線起點距離左側Y軸距離
        xAxis.setSpaceMax(0.5f);//折線終點距離右側Y軸距離

        xAxis.setAxisMinimum(1);//x軸標籤最小值
        xAxis.setAxisMaximum(date.length);//x軸標籤最大值

        xAxis.setGranularity(1);

        xAxis.setDrawGridLines(true);//顯示每個座標點對應X軸的線 (預設顯示)
    }

    private void initY() {
        YAxis rightAxis = lineChart.getAxisRight();//獲取右側的軸線
        rightAxis.setEnabled(false);//不顯示右側Y軸
        YAxis leftAxis = lineChart.getAxisLeft();//獲取左側的軸線

        leftAxis.setLabelCount(13);//Y軸標籤個數
        leftAxis.setTextColor(Color.BLACK);//Y軸標籤顏色
        leftAxis.setTextSize(12);//Y軸標籤大小

        leftAxis.setAxisMinimum(34);//Y軸標籤最小值
        leftAxis.setAxisMaximum(40);//Y軸標籤最大值

        /**
         * 格式化軸標籤二種方式：
         * 1、用圖表庫已寫好的類_如上一步驟中X 軸使用
         * 2、自己實現接口_如下Y 軸使用
         * */
        leftAxis.setValueFormatter(new MyYAxisValueFormatter());
    }

    private class MyYAxisValueFormatter extends ValueFormatter implements IAxisValueFormatter {

        private DecimalFormat mFormat;

        public MyYAxisValueFormatter() {
            mFormat = new DecimalFormat("##.#");//Y軸數值格式及小數點位數
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            // "value" represents the position of the label on the axis (x or y)
            return mFormat.format(value);
        }
    }

    private void initChartFormat() {
        lineChart.setDragEnabled(true);

        lineChart.setScaleXEnabled(true); //打開或關閉x軸的縮放
        lineChart.setScaleYEnabled(false); //打開或關閉y軸的縮放。

        //右下方description label：設置圖表資訊
        Description description = lineChart.getDescription();
        description.setText("體溫/天數");
        description.setTextSize(16);
        description.setTextColor(Color.parseColor("#717171"));
        //左上方Legend：圖例數據資料
        Legend legend = lineChart.getLegend();
        legend.setCustom(new LegendEntry[]{new LegendEntry("早上", Legend.LegendForm.SQUARE, 10, 5, null, Color.parseColor("#35A58D")),
                new LegendEntry("晚上", Legend.LegendForm.SQUARE, 10, 5, null, Color.parseColor("#A60101"))});
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        lineChart.setBackgroundColor(Color.WHITE);//顯示整個圖表背景顏色 (預設灰底)
        legend.setTextSize(16);
        legend.setFormToTextSpace(10);
        //設定沒資料時顯示的內容
        lineChart.setNoDataText("暫時沒有數據");
        lineChart.setNoDataTextColor(Color.BLUE);//文字顏色
        lineChart.setTouchEnabled(true);
    }
}
