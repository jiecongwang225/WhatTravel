package com.example.sunshine.helpers;

import com.example.sunshine.Models.ForecastResults;
import com.example.sunshine.Models.NearbySearchResult;
import com.example.sunshine.Models.NearbySearchResults;
import com.example.sunshine.utils.WTLog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by jiecongwang on 1/25/15.
 */
public class ResponseParser {
    private final static String LOG_TAG = ResponseParser.class.getSimpleName();

    public static NearbySearchResults getNearbySearchResults(String response) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        NearbySearchResults results = gson.fromJson(response,NearbySearchResults.class);
        return results;
    }

    public static ForecastResults getForecastResults(String response) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        WTLog.debug(LOG_TAG,response);
        ForecastResults results = gson.fromJson(response,ForecastResults.class);
        return results;
    }

}
