package com.example.whatTravel.API;

/**
 * Created by jiecongwang on 1/13/15.
 */
public class WTAPIConstants {

    public static final int LOAD_NEARBY_PARK =1;
    public static final String WIKI_BASE_API ="en.wikipedia.org/w/api.php";
    public static final String GOOGLE_PLACES_BASE_API="maps.googleapis.com";
    public static final String WEATHER_API_KEY = "7174bb8131da27792e10a4ff13b5470e";
    public static final String GOOGLE_API_KEY = "AIzaSyDtMiZM2GDAEER8G-5fHbUF-KNNX2blE80";
    public static final String HTTPS ="https";

    public static final int LOAD_WHEATHER_DATA = 2;
    public static final String HTTP ="http";
    public static final String FORECAST_BASE_URL = "api.openweathermap.org/data/2.5";
    public static final String JSON = "json";
    public static final int FORECAST_DAYS_PARAM = 7;

    public static final int LOAD_WIKI_GEO_SEARCH = 4;
    public static final double WIKI_TITLE_SIMILARITY_THRESHOLD = 0.5;
    public static final int LOAD_WIKI_PAGE_SEARCH = 5;
    public static final String SPLIT_TOKEN = "<;>";


    /* Features */

    public static final boolean WIKI_GEO_SEARCH_ENABLED = false;
}
