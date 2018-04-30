package com.xinsane.media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.xinsane.util.LogUtil;
import com.xinsane.util.NotificationUtil;

public class LocalBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.d("onReceive", "LocalBroadcastReceiver");
        String action = intent.getAction();
        if (action == null || action.isEmpty())
            return;
        switch (intent.getAction()) {
            case "com.xinsane.media.notification.dismiss":
                int id = intent.getIntExtra("id", 0);
                LogUtil.d("id = " + id, "LocalBroadcastReceiver");
                NotificationUtil.manager.cancel(id);
                break;
        }
    }

}
