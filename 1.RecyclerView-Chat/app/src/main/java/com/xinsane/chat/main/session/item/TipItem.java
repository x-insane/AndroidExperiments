package com.xinsane.chat.main.session.item;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinsane.chat.R;

public class TipItem implements Item {

    public static int resource_id = R.layout.list_item_session_tip;

    private Drawable img;
    private String tip = "";

    public TipItem() { }
    public TipItem(Drawable img, String tip) {
        this.tip = tip;
        this.img = img;
    }
    public void setImage(Drawable img) { this.img = img; }
    public Drawable getImage() { return img; }
    public void setTip(String tip) { this.tip = tip; }
    public String getTip() { return tip; }

    @Override
    public int resource() {
        return resource_id;
    }

    @Override
    public void sync(View view) {
        ImageView img_tip = view.findViewById(R.id.img_tip);
        img_tip.setImageDrawable(img);
        TextView tv_tip = view.findViewById(R.id.text_tip);
        tv_tip.setText(tip);
    }
}
