package com.example.sunshine.utils;

import android.app.Application;
import android.util.Log;

import com.example.sunshine.context.WTApplication;

/**
 * Created by jiecongwang on 1/12/15.
 */
public class WTLog {

    public static final boolean isDebugMode = Utils.isDebugMode(WTApplication.getInstance().getApplicationContext());

    public static void error(String TAG,String message,Throwable throwable) {
        if (isDebugMode) {
            Log.e(TAG,message,throwable);
        }

    }

    public static void error(String TAG,String message) {
        if (isDebugMode) {
            Log.e(TAG,message);
        }
    }

    public static void info(String TAG,String message) {
        if (isDebugMode) {
            Log.i(TAG,message);
        }
    }

    public static void debug(String TAG,String message) {
        if (isDebugMode) {
            Log.d(TAG,message);
        }
    }

    public static void debug(String TAG,String message,Throwable throwable) {
        if (isDebugMode) {
            Log.d(TAG,message,throwable);
        }
    }
}
