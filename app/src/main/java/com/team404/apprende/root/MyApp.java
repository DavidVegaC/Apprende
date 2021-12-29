package com.team404.apprende.root;

import android.app.Application;
import android.os.SystemClock;

import com.google.firebase.auth.FirebaseAuth;

public class MyApp extends Application {

    //Instanciar al iniciar aplicación
    FirebaseAuth firebaseAuth;
    private static final long SPLASH_SCREEN_DELAY = 1500;
    @Override
    public void onCreate() {
        super.onCreate();

        firebaseAuth = FirebaseAuth.getInstance();

        SystemClock.sleep(SPLASH_SCREEN_DELAY);
    }
}
