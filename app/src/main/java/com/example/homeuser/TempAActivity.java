package com.example.homeuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeuser.Prevalent.Prevalent;
import com.example.homeuser.sql.DbHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

public class TempAActivity extends AppCompatActivity {
    private ListView devicesListView;
    private TextView temp_double;
    private BluetoothAdapter mBTAdapter;
    private ArrayAdapter <String> mBTArrayAdapter;
    private BluetoothSocket mBTSocket = null;
    private Handler mHandler;
    private Handler handler;

    private ConnectedThread mConnectedThread;
    private static final UUID BTMODULEUUID = UUID.fromString ("00001101-0000-1000-8000-00805F9B34FB"); // "random" unique identifier
    private Button discover_btn;
    private Set<BluetoothDevice> mPairedDevices;
    private final static int REQUEST_ENABLE_BT = 1;



    private final static int STATE_CONNECTING = 1;
    private final static int STATE_CONNECTED = 2;
    private final static int STATE_CONNECTION_FAIL = 3;
    private final static int STATE_BT_NOT_ON = 4;

    static final int STATE_MESSAGE_RECEIVED = 1;

    private TextView timeText;
    DatabaseReference databaseReference;

    DbHelper dbHelper;

    Temperature tempEntity;
    Users users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_a);


        discover_btn = (Button)findViewById(R.id.discover_btn);
        temp_double = (TextView)findViewById(R.id.temp_double);

        timeText = (TextView)findViewById(R.id.time);

        mBTAdapter = BluetoothAdapter.getDefaultAdapter();
        mBTArrayAdapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1);

        devicesListView = (ListView)findViewById(R.id.devicesListView);
        devicesListView.setAdapter(mBTArrayAdapter); // assign model to view
        devicesListView.setOnItemClickListener(mDeviceClickListener);

        dbHelper = new DbHelper(this);
        users = new Users();
        tempEntity = new Temperature();

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        mHandler = new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what)
                {
                    case STATE_CONNECTING:
                        Toast.makeText(getBaseContext(), "連接中...", Toast.LENGTH_SHORT).show();
                        break;
                    case STATE_CONNECTED:
                        Toast.makeText(getBaseContext(), "藍牙已連線", Toast.LENGTH_SHORT).show();
                        break;
                    case STATE_CONNECTION_FAIL:
                        Toast.makeText(getBaseContext(), "連線失敗", Toast.LENGTH_SHORT).show();
                        break;
                    case STATE_BT_NOT_ON:
                        Toast.makeText(getBaseContext(), "藍牙未開啟", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what)
                {
                    case STATE_MESSAGE_RECEIVED:
                        byte[] readBuffer = (byte[])msg.obj;
                        final String temp = new String(readBuffer, 0, msg.arg1);

                        temp_double.setText(temp + "°C");

                        databaseReference = FirebaseDatabase.getInstance().getReference("HomeUsers");
                        Date now = new Date();
                        long timestamp = now.getTime();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.TAIWAN);
                        String date = sdf.format(timestamp);

                        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss", Locale.TAIWAN);
                        String time = sdf2.format(timestamp);

                        if(temp.length() > 2) {
                            Intent intent = getIntent();
                            final String phone = intent.getStringExtra("phone");

                            tempEntity.setDate(date);
                            tempEntity.setTime(time);
                            tempEntity.setTemp(temp);
                            tempEntity.setUser_phone(phone);
                            dbHelper.addTemp(tempEntity);

                            databaseReference.child(Prevalent.currentonlineusers.getPhone()).child("temperature").child(date + "-" + time).setValue(temp + "°C");
                            break;
                        }
                }
                return true;
            }
        });

        // set actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("額溫測量");
        actionBar.setDisplayShowHomeEnabled(true);  // back button
        actionBar.setDisplayHomeAsUpEnabled(true);

        discover_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listPairedDevices(v);
            }
        });
    }

    //back to the previous fragment
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
    private AdapterView.OnItemClickListener mDeviceClickListener = new
            AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {

                    if(!mBTAdapter.isEnabled()) {
                        handler_msg(4);       //BT_NOT_ON = 4
                        return;
                    } else {

                        handler_msg(1);            //Connecting = 1

                        // Get the device MAC address, which is the last 17 chars in the View
                        String info = ((TextView) v).getText().toString();
                        final String address = info.substring(info.length() - 17);
                        final String name = info.substring(0, info.length() - 17);

                        // Spawn a new thread to avoid blocking the GUI one
                        new Thread() {
                            public void run() {
                                boolean fail = false;
                                //取得裝置MAC找到連接的藍芽裝置
                                BluetoothDevice device = mBTAdapter.getRemoteDevice(address);

                                try {
                                    mBTSocket = createBluetoothSocket(device);
                                    //建立藍芽socket
                                } catch (IOException e) {
                                    fail = true;
                                    Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_SHORT).show();
                                }
                                // Establish the Bluetooth socket connection.
                                try {
                                    mBTSocket.connect(); //建立藍芽連線
                                } catch (IOException e) {
                                    try {
                                        fail = true;
                                        mBTSocket.close(); //關閉socket
                                        //開啟執行緒 顯示訊息
                                        handler_msg(3);                 //CONNECTION FAIL = 3

                                    } catch (IOException e2) {
                                        Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                if (fail == false) {
                                    //開啟執行緒用於傳輸及接收資料
                                    mConnectedThread = new ConnectedThread(mBTSocket);
                                    mConnectedThread.start();
                                    //開啟新執行緒顯示連接裝置名稱
                                    handler_msg(2);                  //CONNECTED = 2
                                }
                            }
                        }.start();
                    }
                }
            };

    public void handler_msg(final int m){
        Message message = Message.obtain();
        message.what = m;
        mHandler.sendMessage(message);
    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws
            IOException {
        return  device.createRfcommSocketToServiceRecord(BTMODULEUUID);
        //creates secure outgoing connection with BT device using UUID
    }

    private void listPairedDevices(View v){
        mPairedDevices = mBTAdapter.getBondedDevices();
        mBTArrayAdapter.clear();
        if(mBTAdapter.isEnabled()) {
            // put it's one to the adapter
            for (BluetoothDevice device : mPairedDevices)
                mBTArrayAdapter.add(device.getName() + "\n" + device.getAddress());

            Toast.makeText(getApplicationContext(), "顯示已配對裝置", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "藍牙未開啟", Toast.LENGTH_SHORT).show();
            request_turnONBT();
        }
    }

    private void request_turnONBT(){
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);    //跳出視窗
        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);                     //開啟設定藍芽畫面
    }

    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream inputStream;

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;

            try {
                tmpIn = mmSocket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            inputStream = tmpIn;
        }

        public void run() {
            byte[] buffer = new byte[1024];
            int bytes;

            while (true)
            {
                try{
                    bytes = inputStream.read(buffer);
                    handler.obtainMessage(STATE_MESSAGE_RECEIVED,bytes,-1,buffer).sendToTarget();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


}