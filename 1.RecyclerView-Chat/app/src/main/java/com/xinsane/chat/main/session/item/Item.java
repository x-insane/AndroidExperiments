package com.xinsane.chat.main.session.item;

import android.view.View;

public interface Item {
    int resource(); // 返回资源id
    void sync(View view); // 向item view中写入数据
}
