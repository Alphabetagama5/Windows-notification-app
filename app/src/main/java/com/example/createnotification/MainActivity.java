package com.example.createnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(MainActivity.this,android.Manifest.permission.POST_NOTIFICATIONS)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.POST_NOTIFICATIONS},101);
            }
        }

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayNotification();
            }
        });
    }

    private void displayNotification() {
        String CHANNEL_ID = "SIMPLE NOTIFICATION ID";
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_11_svgrepo_com)
                .setContentTitle("My Notification")
                .setContentText("Some notification text")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent=new Intent(getApplicationContext(), Notify.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("data","Value is passed here");

        PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_MUTABLE);
        mBuilder.setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        if (android.os.Build.VERSION.SDK_INT >=android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel =  manager.getNotificationChannel(CHANNEL_ID);
            if (channel==null){
                int importance=NotificationManager.IMPORTANCE_HIGH;
                channel=new NotificationChannel(CHANNEL_ID,"some description",importance);
                channel.setLightColor(Color.YELLOW);
                channel.enableVibration(true);
                manager.createNotificationChannel(channel);

            }
        }
        manager.notify(0,mBuilder.build());


    }
}