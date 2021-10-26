package com.team404.apprende;

import android.app.Application;
import android.os.SystemClock;

public class MyApp extends Application {

    private static final long SPLASH_SCREEN_DELAY = 1500;
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(SPLASH_SCREEN_DELAY);
    }
}
