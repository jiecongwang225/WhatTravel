package com.example.sunshine.API;

/**
 * Created by jiecongwang on 1/13/15.
 */
public enum WTApiUrlType {
    WEATHER(WTAPIConstants.FORECAST_BASE_URL),
    WIKI(WTAPIConstants.WIKI_BASE_API),
    GOOGLE_PLACE(WTAPIConstants.GOOGLE_PLACES_BASE_API);

    private String baseUrl;
    private WTApiUrlType(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

}
