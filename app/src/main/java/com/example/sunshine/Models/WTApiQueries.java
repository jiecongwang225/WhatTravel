package com.example.sunshine.Models;

import android.net.Uri;

import com.example.sunshine.utils.WTLog;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created by jiecongwang on 1/13/15.
 */
public abstract class WTApiQueries {
    private final static String TAG ="WTAPIQueries";
    protected List<String> paths = Lists.newArrayList();

    protected Map<String,String> params = Maps.newHashMap();





    protected abstract String getBaseApiUrl();

    protected abstract String getHttpScheme();


    public String getRequestUrl() {
       Uri.Builder uriBuilder = new Uri.Builder();
       uriBuilder.scheme(getHttpScheme()).authority(getBaseApiUrl());
       for (String path :paths) {
           uriBuilder.appendPath(path);
       }

       for (Map.Entry<String,String> entry : params.entrySet()) {
           uriBuilder.appendQueryParameter(entry.getKey(),entry.getValue());
       }
        uriBuilder.build();
        WTLog.debug(TAG,uriBuilder.toString());
       return uriBuilder.toString();
    }




}
