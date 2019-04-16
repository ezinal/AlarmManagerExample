package com.example.emre.alarmdeneme;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import static android.support.v4.content.ContextCompat.getSystemService;


public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "CHANNELID";
    MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent) {
        createNotificationChannel(context);

        mp = MediaPlayer.create(context, R.raw.definite);
        mp.start();

        Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();

        NotificationCompat.Builder builder =new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_compare_arrows_black_18dp)
                .setContentTitle("Sample Not. Title")
                .setContentText("Sample Notification Text")
                .setStyle(new NotificationCompat.BigTextStyle());

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(12345, builder.build());

    }

    private void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel Name";
            String description = "channel Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(context,NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}