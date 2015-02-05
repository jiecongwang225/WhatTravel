package com.example.whatTravel.context;

import android.app.Application;
import android.content.Context;

/**
 * Created by jiecongwang on 1/12/15.
 */
public class WTApplication extends Application {

    private Context mContext;
    private static Application Instance;

    @Override
    public void onCreate() {
        super.onCreate();
        Instance = this;
    }

    public static Application getInstance() {
        return Instance;
    }




}
