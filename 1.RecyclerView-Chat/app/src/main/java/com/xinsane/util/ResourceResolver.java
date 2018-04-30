package com.xinsane.util;

import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

import com.xinsane.chat.App;

public class ResourceResolver {

    public static Drawable getDrawableFromResourceId(int resource_id) {
        return ResourcesCompat.getDrawable(App.getContext().getResources(),
                resource_id, null);
    }

}
