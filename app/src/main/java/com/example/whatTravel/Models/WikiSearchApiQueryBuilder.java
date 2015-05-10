package com.example.whatTravel.Models;

import com.example.whatTravel.API.WTAPIConstants;

/**
 * Created by dlu on 3/8/15.
 */
public class WikiSearchApiQueryBuilder extends WTApiQueries{
    public static final String ACTION_PARAM = "action";
    public static final String FORMAT_PARAM = "format";
    public static final String LIST_PARAM = "list";
    public static final String GSRADIUS_PARAM = "gsradius";
    public static final String GSCOORD_PARAM = "gscoord";

    public WikiSearchApiQueryBuilder() {
        super();
    }

    public void setActionParam(String action) { params.put(ACTION_PARAM, action); }
    public void setFormatParam(String format) { params.put(FORMAT_PARAM, format); }
    public void setListParam(String list) { params.put(LIST_PARAM, list); }
    public void setGsradiusParam(String gsradius) { params.put(GSRADIUS_PARAM, gsradius); }
    public void setGscoordParam(String gscoord) { params.put(GSCOORD_PARAM, gscoord); }

    @Override
    protected String getBaseApiUrl() {
        return WTAPIConstants.WIKI_BASE_API;
    }

    @Override
    protected String getHttpScheme() {
        return WTAPIConstants.HTTPS;
    }
}
