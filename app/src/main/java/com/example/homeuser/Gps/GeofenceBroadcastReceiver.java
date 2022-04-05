package com.example.homeuser.Gps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.homeuser.Prevalent.Prevalent;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "GeofenceBroadcastReceive";
    DatabaseReference def;

    private static final int GEOFENCE_TRANSITION_ENTER = 1;
    private static final int GEOFENCE_TRANSITION_EXIT = 2;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        Toast.makeText(context, "Geofence triggered...", Toast.LENGTH_SHORT).show();

        NotificationHelper notificationHelper = new NotificationHelper(context);

        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);

        if (geofencingEvent.hasError()) {
            Log.d(TAG, "onReceive: Error receiving geofence event...");
            return;
        }

        List<Geofence> geofenceList = geofencingEvent.getTriggeringGeofences();
        for (Geofence geofence: geofenceList) {
            Log.d(TAG, "onReceive: " + geofence.getRequestId());
        }

        int transitionType = geofencingEvent.getGeofenceTransition();

        def = FirebaseDatabase.getInstance().getReference("HomeUsers");
        Date now = new Date();
        long timestamp = now.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss", Locale.TAIWAN);
        String date = sdf.format(timestamp);

        switch (transitionType) {
            case GEOFENCE_TRANSITION_ENTER:
                Toast.makeText(context, "很棒喔!有乖乖待在家", Toast.LENGTH_SHORT).show();
                notificationHelper.sendHighPriorityNotification("很棒喔!有乖乖待在家", "", GpsBActivity.class);

                def.child("Status").child(Prevalent.currentonlineusers.getPhone()).child(date).setValue("很棒喔!有乖乖待在家");
                break;
            case GEOFENCE_TRANSITION_EXIT:
                Toast.makeText(context, "已離開管制區，請返回住家", Toast.LENGTH_SHORT).show();
                notificationHelper.sendHighPriorityNotification("已離開管制區，請返回住家", "", GpsBActivity.class);

                def.child("Status").child(Prevalent.currentonlineusers.getPhone()).child(date).setValue("離開管制範圍");
                break;
        }
    }
}
