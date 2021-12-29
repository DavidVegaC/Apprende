package com.team404.apprende.root;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;

public class App extends Application {

    //Instanciar instancia inicial
    FirebaseAuth firebaseAuth;

    @Override
    public void onCreate() {
        super.onCreate();

        firebaseAuth = FirebaseAuth.getInstance();

    }
}
