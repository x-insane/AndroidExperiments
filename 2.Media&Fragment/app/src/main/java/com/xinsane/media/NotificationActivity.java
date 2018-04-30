package com.xinsane.media;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.xinsane.util.LogUtil;
import com.xinsane.util.NotificationUtil;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        TextView textView = findViewById(R.id.text);
        Intent intent = getIntent();
        if (intent != null) {
            int id = intent.getIntExtra("notification_id", 0);
            LogUtil.d("notification_id = " + id, "NotificationActivity");
            if (id > 0)
                NotificationUtil.manager.cancel(id);
            StringBuilder buffer = new StringBuilder();
            String data = intent.getStringExtra("data");
            if (data != null)
                buffer.append("data = ").append(data).append("\n");
            String data2 = intent.getStringExtra("data2");
            if (data2 != null)
                buffer.append("data2 = ").append(data2);
            textView.setText(buffer);
        }
    }
}
