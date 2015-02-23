package com.example.whatTravel.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import java.util.Collection;

/**
 * Created by jiecongwang on 1/12/15.
 */
public class Utils {
    // is DebugMode or Release mode
    public static boolean isDebugMode(Context context) {
        return  (0 != (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
    }

    public static boolean isEmptyString(String text) {
        return text == null || text.trim().isEmpty();
    }

    public static boolean isEmptySafe(Collection collection) {
        return collection == null || collection.isEmpty();
    }
}
