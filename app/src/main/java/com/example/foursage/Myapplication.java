package com.example.foursage;

import android.app.Application;
import android.content.Context;

/**
 * Created by 袁凯明 on 2018/2/28.
 */

public class Myapplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
