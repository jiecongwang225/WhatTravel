package com.example.sunshine.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * Created by jiecongwang on 1/12/15.
 */
public class Utils {
    // is DebugMode or Release mode
    public static boolean isDebugMode(Context context) {
        return  (0 != (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
    }
}
