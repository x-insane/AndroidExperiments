package com.xinsane.chat.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.xinsane.chat.R;
import com.xinsane.chat.main.session.SessionListAdapter;
import com.xinsane.chat.main.session.item.Item;
import com.xinsane.chat.main.session.item.SessionItem;
import com.xinsane.chat.main.session.item.TipItem;
import com.xinsane.util.ResourceResolver;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Item> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        toolbar.inflateMenu(R.menu.menu_main);
        initList();
        RecyclerView recyclerView = findViewById(R.id.main_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SessionListAdapter(list, this));
    }

    @SuppressLint("DefaultLocale")
    private void initList() {
        list = new LinkedList<>();
        list.add(new TipItem(ResourceResolver.getDrawableFromResourceId(R.drawable.ic_computer_black_24dp),
                "Windows 微信已登录"));
        list.add(new TipItem(ResourceResolver.getDrawableFromResourceId(R.drawable.ic_music_note_black_24dp),
                "正在播放 青藏高原"));

        Item[] items = {
            new SessionItem(ResourceResolver.getDrawableFromResourceId(R.drawable.avatar_15698),
                "xinsane", "哈哈", "17:57"),
            new SessionItem(ResourceResolver.getDrawableFromResourceId(R.drawable.avatar_11802),
                "黄瑞斌", "楞个大", "17:57"),
            new SessionItem(ResourceResolver.getDrawableFromResourceId(R.drawable.avatar_12231),
                "陈素琪", "哈哈哈哈", "17:57"),
            new SessionItem(ResourceResolver.getDrawableFromResourceId(R.drawable.avatar_33727),
                "陈泽锋", "好啊好啊", "17:57")
        };
        Random rand = new Random(System.currentTimeMillis());
        for (int i=0;i<25;++i)
            list.add(items[rand.nextInt(items.length)]);
        list.add(new SessionItem(ResourceResolver.getDrawableFromResourceId(R.drawable.avatar_15698),
                "这个人的名字稍微有那么一点点长", "发送的消息也有那么一点点的长一点点的长", "17:57"));
        for (int i=0;i<25;++i)
            list.add(items[rand.nextInt(items.length)]);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
