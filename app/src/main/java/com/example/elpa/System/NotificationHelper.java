package com.example.elpa.System;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.elpa.R;
import com.example.elpa.User.MainActivity;
import com.example.elpa.User.terimakasih;

public class NotificationHelper extends ContextWrapper {
    private final String CHANNEL_ID =  "register_notification";
    private final String CHANNEL_NAME =  "Register";
    private final String CHANNEL_IDS = "confirmation_notification";
    private final String CHANNEL_NAMES =  "Confirmation";
//    private final String CHANNEL_IDMENUUTAMA = "Home_notification";
//    private final String CHANNEL_NAMEMENUUTAMA =  "Home";


    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel();
        }
    }
    @TargetApi(Build.VERSION_CODES.O)
    public void createChannel(){
        NotificationChannel register = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        register.enableLights(true);
        register.enableVibration(true);
        register.setLightColor(R.color.colorPrimary);
        register.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(register);

        NotificationChannel confirmation = new NotificationChannel(CHANNEL_IDS,CHANNEL_NAMES, NotificationManager.IMPORTANCE_DEFAULT);
        confirmation.enableLights(true);
        confirmation.enableVibration(true);
        confirmation.setLightColor(R.color.colorPrimary);
        confirmation.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(confirmation);

//        NotificationChannel menuutama = new NotificationChannel(CHANNEL_IDMENUUTAMA,CHANNEL_NAMEMENUUTAMA, NotificationManager.IMPORTANCE_DEFAULT);
//        menuutama.enableLights(true);
//        menuutama.enableVibration(true);
//        menuutama.setLightColor(R.color.colorPrimary);
//        menuutama.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
//
//        getManager().createNotificationChannel(menuutama);
    }

    public NotificationManager getManager(){
        if(mManager == null){
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public NotificationCompat.Builder getRegnotif(String title,String message) {
        Intent intent = new Intent(this, Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent regintent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationHelper call = new NotificationHelper(this);

        return new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID)
                .setSmallIcon(R.drawable.elpalogorevisi)
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(regintent)
                .setAutoCancel(true);
    }
    public NotificationCompat.Builder getConfirmnotif(String title,String message, int bg, String randomtoken) {
        //udah mau tapi cuma masalah di HOME !!!
        Intent intent = new Intent(NotificationHelper.this, terimakasih.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("MyImageResource", bg);
        intent.putExtra("token",randomtoken);
        PendingIntent confirmintent = PendingIntent.getActivity(NotificationHelper.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_IDS)
                    .setSmallIcon(R.drawable.elpalogorevisi)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(message))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(confirmintent)
                    .setAutoCancel(true);
    }
//    public NotificationCompat.Builder getmenunotif(String title,String message) {
//        Intent intent = new Intent(NotificationHelper.this, terimakasih.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        PendingIntent confirmintent = PendingIntent.getActivity(NotificationHelper.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//
//        return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_IDS)
//                .setSmallIcon(R.drawable.elpa)
//                .setContentTitle(title)
//                .setContentText(message)
//                .setStyle(new NotificationCompat.BigTextStyle()
//                        .bigText(message))
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .setContentIntent(confirmintent)
//                .setAutoCancel(true);
//    }
}