package com.xinsane.chat;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static App app;
    private static Context context;

    public static App getApp() {
        if (app == null) {
            synchronized (App.class) {
                if (app == null)
                    app = new App();
            }
        }
        return app;
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

}
