package com.xinsane.util;

import android.app.Application;
import android.content.Context;

public class AppUtil extends Application {

    private static Application app;
    private static Context context;

    public static Application getApp() {
        return app;
    }
    public static Context getContext() {
        return context;
    }

    public static void onCreate(Application app, Context context) {
        AppUtil.app = app;
        AppUtil.context = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        context = getApplicationContext();
    }

}
