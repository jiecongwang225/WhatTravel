package com.example.whatTravel.Models;

import com.example.whatTravel.API.WTAPIConstants;

/**
 * Created by Danjie on 1/31/2015.
 */
public class ForecastSearch extends WTApiQueries {
    public static final String QUERY_PARAM = "q";
    public static final String FORMAT_PARAM = "mode";
    public static final String UNITS_PARAM = "units";
    public static final String DAYS_PARAM = "cnt";


    private static final String FORECAST = "forecast";
    private static final String DAILY ="daily";

    public ForecastSearch() {
        super();
        paths.add(FORECAST);
        paths.add(DAILY);
    }

    public void setQuery(String query) {
        params.put(QUERY_PARAM, query);
    }

    public void setFormat(String format) {
        params.put(FORMAT_PARAM, format);
    }

    public void setUnits(String units) {
        params.put(UNITS_PARAM, units);
    }

    public void setDays(int days) {
        params.put(DAYS_PARAM, Integer.toString(days));
    }

    @Override
    protected String getBaseApiUrl() {
        return WTAPIConstants.FORECAST_BASE_URL;
    }

    @Override
    protected String getHttpScheme() {
        return WTAPIConstants.HTTP;
    }


}
