package com.xinsane.chat.main.session.item;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinsane.chat.R;
import com.xinsane.util.ResourceResolver;

public class SessionItem implements Item {

    public static int resource_id = R.layout.list_item_session;

    private Drawable avatar = ResourceResolver.getDrawableFromResourceId(R.drawable.ic_person_black_24dp);
    private String title = "", message = "", time="";

    public SessionItem() { }
    public SessionItem(Drawable avatar, String title, String message, String time) {
        this.avatar = avatar;
        this.title = title;
        this.message = message;
        this.time = time;
    }

    public Drawable getAvatar() {
        return avatar;
    }
    public void setAvatar(Drawable avatar) {
        this.avatar = avatar;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int resource() {
        return resource_id;
    }

    @Override
    public void sync(View view) {
        ImageView img_avatar = view.findViewById(R.id.img_avatar);
        TextView text_title = view.findViewById(R.id.text_title);
        TextView text_message = view.findViewById(R.id.text_message);
        TextView text_time = view.findViewById(R.id.text_time);
        img_avatar.setImageDrawable(avatar);
        text_title.setText(title);
        text_message.setText(message);
        text_time.setText(time);
    }

}
