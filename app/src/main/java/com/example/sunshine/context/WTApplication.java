package com.example.sunshine.context;

import android.app.Application;
import android.content.Context;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

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
