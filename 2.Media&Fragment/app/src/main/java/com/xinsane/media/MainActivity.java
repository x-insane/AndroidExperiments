package com.xinsane.media;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import com.xinsane.media.fragment.FragmentActivity;
import com.xinsane.util.LogUtil;
import com.xinsane.util.NotificationUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String data = getIntent().getStringExtra("data");
        if (data != null)
            LogUtil.d(data, "pi");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String data = intent.getStringExtra("data");
        if (data != null)
            LogUtil.d(data, "pi");
    }

    public void onNotify(View view) {
        new NotificationUtil(1, "通知标题", "这是通知的内容")
                .setIsAutoSend(true)
                .setBigText("这是通知的内容这是通知的内容这是通知的内容这是通知的内容这是通知的内容这是通知的内容这是通知的内容这是通知的内容这是通知的内容这是通知的内容这是通知的内容这是通知的内容这是通知的内容这是通知的内容这是通知的内容这是通知的内容这是通知的内容这是通知的内容这是通知的内容")
//                .setBigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.wallpaper_5252698), "summary")
                .setCallbackActivity(MainActivity.class, new Intent(this, NotificationActivity.class)
                    .putExtra("data", "MyData2")
                    .putExtra("data2", "value2"))
                .setSound("/system/media/audio/ringtones/Mi.ogg")
                .setVibrate(500, 1000, 5)
                .setLargeIcon(R.drawable.a)
                .setSmallIcon(R.drawable.icon)
                .setTicker("你有一条新消息")
                .setLights(Color.GREEN, 1000, 1000)
                .build();
    }

    public void onCustomNotify(View view) {
        RemoteViews remoteViews = new RemoteViews("com.xinsane.media", R.layout.notification_button_layout);
        remoteViews.setTextViewText(R.id.text_title, "通知标题");
        remoteViews.setTextViewText(R.id.text_content, "这是通知的内容这是通知的内容这是通知的内容这是通知的内容这是通知的内容这是通知的内容这是通知的内容这是通知的内容这是通知的内容这是通知");
        remoteViews.setOnClickPendingIntent(R.id.btn_cancel, PendingIntent.getBroadcast(this, 0,
                new Intent("com.xinsane.media.notification.dismiss")
                        .setClass(this, LocalBroadcastReceiver.class)
                        .putExtra("id", 2), PendingIntent.FLAG_UPDATE_CURRENT));
        remoteViews.setOnClickPendingIntent(R.id.btn_submit, PendingIntent.getActivity(this, 0,
                new Intent(this, NotificationActivity.class)
                    .putExtra("data", "MyDat23a")
                    .putExtra("data2", "valu31e")
                    .putExtra("notification_id", 2), PendingIntent.FLAG_UPDATE_CURRENT));
        new NotificationUtil(2, null, null)
                .setIsAutoSend(true)
                .setLargeIcon(R.drawable.a)
                .setSmallIcon(R.drawable.icon)
                .setCustomContentView(remoteViews)
                .setCustomBigContentView(remoteViews)
                .setTicker("这是通知的内容Ticker")
                .setAutoCancel(true)
                .build();
    }

    public void startPhotoActivity(View view) {
        startActivity(new Intent(this, TakePhotoActivity.class));
    }

    public void startChoosePhotoActivity(View view) {
        startActivity(new Intent(this, OpenAlbumActivity.class));
    }

    public void startScannerActivity(View view) {
        startActivity(new Intent(this, ScannerActivity.class));
    }

    public void startFragmentActivity(View view) {
        startActivity(new Intent(this, FragmentActivity.class));
    }
}
