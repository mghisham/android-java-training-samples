package com.example.myapps;

import android.app.Application;

public class ApplicationBase extends Application {
    public void onCreate() {
        super.onCreate();
        //Stetho.initializeWithDefaults(this);
    }
}