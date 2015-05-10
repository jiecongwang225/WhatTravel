package com.example.whatTravel.Models.wiki;

import com.example.whatTravel.API.WTAPIConstants;
import com.example.whatTravel.Models.WTApiQueries;

/**
 * Created by dlu on 4/11/15.
 */
public class WikiGeoSearch extends WTApiQueries {
    public static final String ACTION_PARAM = "action";
    public static final String FORMAT_PARAM = "format";
    public static final String LIST_PARAM = "list";
    public static final String GSRADIUS_PARAM = "gsradius";
    public static final String GSCOORD_PARAM = "gscoord";


    public WikiGeoSearch() {
        super();

    }

    @Override
    protected String getBaseApiUrl() {
        return WTAPIConstants.WIKI_BASE_API;
    }

    @Override
    protected String getHttpScheme() {
        return WTAPIConstants.HTTPS;
    }

    public void setAction(String query)
    {
        params.put(ACTION_PARAM, query);
    }

    public void setFormat(String format)
    {
        params.put(FORMAT_PARAM, format);
    }

    public void setList(String list)
    {
        params.put(LIST_PARAM, list);
    }

    public void setGsradius(int gsradius)
    {
        params.put(GSRADIUS_PARAM, Integer.toString(gsradius));
    }

    public void setGscoord(String gscoord)
    {
        params.put(GSCOORD_PARAM, gscoord);
    }

}
