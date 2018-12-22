package com.example.twtech.date19aprl;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class MyService extends Service {

    private Handler h;
    private Runnable r;

    int counter = 0;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Notification updateNotification() {
        counter++;

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        String info = counter + "";
        return new NotificationCompat.Builder(this)
                .setContentTitle(info)
                .setTicker(info)
                .setContentText(info)
                .setSmallIcon(R.drawable.music)
                .setLargeIcon(
                        Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),
                                R.drawable.music), 128, 128, false))
                .setContentIntent(pendingIntent)
                .setOngoing(true).build();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.getAction().contains("start")) {
            h = new Handler();
            r = new Runnable() {
                @Override
                public void run() {
                    startForeground(101, updateNotification());
                    Toast.makeText(MyService.this, "Service still running", Toast.LENGTH_SHORT).show();
                    h.postDelayed(this, 1000);
                }
            };

            h.post(r);
        } else {
            h.removeCallbacks(r);
            stopForeground(true);
            stopSelf();
        }

        return Service.START_STICKY;
    }
}
