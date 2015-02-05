package com.example.whatTravel.Models;

import com.example.whatTravel.API.WTAPIConstants;

/**
 * Created by jiecongwang on 1/13/15.
 */
public abstract class GooglePlaces extends WTApiQueries {

    private static final String MAPS ="maps";
    private static final String PLACE ="place";
    private static final String API ="api";
    private static final String OUTPUT = "json";

    public GooglePlaces()
    {
        super();
        paths.add(MAPS);
        paths.add(API);
        paths.add(PLACE);
        paths.add(getSearchType());
        paths.add(OUTPUT);

    }

    @Override
    protected String getBaseApiUrl() {
        return WTAPIConstants.GOOGLE_PLACES_BASE_API;
    }

    @Override
    protected String getHttpScheme() {
        return WTAPIConstants.HTTPS;
    }

    protected abstract String getSearchType();

}
