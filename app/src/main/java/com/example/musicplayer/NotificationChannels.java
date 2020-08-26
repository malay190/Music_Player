package com.example.musicplayer;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationChannels extends Application {

    public static final String CHANNEL = "channel";

    @Override
    public void onCreate() {
        super.onCreate();
        notificationChannel();
    }

    private void notificationChannel(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL,
                    "channel",NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("mediaPlayer");

            NotificationManager notificationManager =getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
