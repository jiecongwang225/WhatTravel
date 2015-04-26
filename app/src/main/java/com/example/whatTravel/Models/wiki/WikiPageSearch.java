package com.example.whatTravel.Models.wiki;

import com.example.whatTravel.API.WTAPIConstants;
import com.example.whatTravel.Models.WTApiQueries;

/**
 * Created by dlu on 4/12/15.
 */
public class WikiPageSearch extends WTApiQueries {
    public static final String ACTION_PARAM = "action";
    public static final String FORMAT_PARAM = "format";
    public static final String PROP_PARAM = "prop";
    public static final String INPROP_PARAM = "inprop";
    public static final String PAGEIDS_PARAM = "pageids";



    public WikiPageSearch() {
        super();
    }

    @Override
    protected String getBaseApiUrl() {
        return WTAPIConstants.WIKI_BASE_API;
    }

    @Override
    protected String getHttpScheme() {
        return WTAPIConstants.HTTP;
    }

    public void setAction(String query)
    {
        params.put(ACTION_PARAM, query);
    }

    public void setFormat(String format)
    {
        params.put(FORMAT_PARAM, format);
    }

    public void setProp(String prop)
    {
        params.put(PROP_PARAM, prop);
    }

    public void setInprop(String inprop)
    {
        params.put(INPROP_PARAM, inprop);
    }

    public void setPageIds(int pageIds)
    {
        params.put(PAGEIDS_PARAM, String.valueOf(pageIds));
    }
}
