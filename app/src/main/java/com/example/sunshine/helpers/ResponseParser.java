package com.example.sunshine.helpers;

import com.example.sunshine.Models.NearbySearchResult;
import com.example.sunshine.Models.NearbySearchResults;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by jiecongwang on 1/25/15.
 */
public class ResponseParser {

    public static NearbySearchResults getNearbySearchResults(String response) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        NearbySearchResults results = gson.fromJson(response,NearbySearchResults.class);
        return results;
    }

}
