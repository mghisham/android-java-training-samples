package com.example.myapps;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class ApplicationBase extends Application {
    public void onCreate() {
        super.onCreate();
        //Stetho.initializeWithDefaults(this);
    }
}