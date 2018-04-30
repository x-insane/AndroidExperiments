package com.xinsane.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import java.io.File;

public class NotificationUtil extends NotificationCompat.Builder {

    public static NotificationManager manager = (NotificationManager) AppUtil.getContext()
            .getSystemService(Context.NOTIFICATION_SERVICE);

    static {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel("default",
                    "Default", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);
            channel.setLightColor(Color.GREEN);
            channel.setShowBadge(true);
            manager.createNotificationChannel(channel);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel("important",
                    "Important", NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.setShowBadge(true);
            manager.createNotificationChannel(channel);
        }
    }

    public static void addChannel(String id, String name) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel(id,
                    name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.setShowBadge(true);
            manager.createNotificationChannel(channel);
        }
    }

    public static void addChannel(NotificationChannel channel) {
        if (Build.VERSION.SDK_INT >= 26)
            manager.createNotificationChannel(channel);
    }

    private int notification_id;
    private boolean is_auto_send = true;

    public NotificationUtil(int id, String title, String content) {
        super(AppUtil.getContext(), "default");
        notification_id = id;
        setContentTitle(title);
        if (content != null && !content.isEmpty())
            setContentText(content);
        setWhen(System.currentTimeMillis());
        setAutoCancel(true);
    }

    public NotificationUtil setCallbackActivity(Class<?> cls, Intent intent) {
        if (intent == null)
            intent = new Intent(AppUtil.getContext(), cls);
        setContentIntent(PendingIntent.getActivity(AppUtil.getContext(), 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT));
        return this;
    }

    public NotificationUtil setSound(String filename) {
        setSound(Uri.fromFile(new File(filename)));
        return this;
    }

    public NotificationUtil setLargeIcon(int resource_id) {
        setLargeIcon(BitmapFactory.decodeResource(AppUtil.getContext().getResources(), resource_id));
        return this;
    }

    public NotificationUtil setVibrate(long period, long interval, int times) {
        long[] d = new long[times*2];
        d[0] = 0;
        for (int i=1;i<times;++i) {
            d[i*2-1] = period;
            d[i*2] = interval;
        }
        d[times*2-1] = period;
        setVibrate(d);
        return this;
    }

    public NotificationUtil setBigText(String bigText) {
        setStyle(new NotificationCompat.BigTextStyle().bigText(bigText));
        return this;
    }

    public NotificationUtil setBigPicture(Bitmap bigPicture) {
        setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bigPicture));
        return this;
    }
    public NotificationUtil setBigPicture(Bitmap bigPicture, String summary) {
        setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bigPicture).setSummaryText(summary));
        return this;
    }

    public NotificationUtil setIsAutoSend(boolean is_auto_send) {
        this.is_auto_send = is_auto_send;
        return this;
    }

    @Override
    public NotificationCompat.Builder setTicker(CharSequence tickerText) {
        if (Build.VERSION.SDK_INT >= 26) {
            setContentTitle(tickerText);
            setChannelId("important");
        }
        else if (Build.VERSION.SDK_INT >= 16)
            setPriority(Notification.PRIORITY_MAX);
        return super.setTicker(tickerText);
    }

    @Override
    public Notification build() {
        Notification notification = super.build();
        if (is_auto_send)
            manager.notify(notification_id, notification);
        return notification;
    }

}
