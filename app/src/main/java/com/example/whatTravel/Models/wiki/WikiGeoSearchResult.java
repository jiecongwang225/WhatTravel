package com.example.whatTravel.Models.wiki;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dlu on 4/12/15.
 */
public class WikiGeoSearchResult {
    @SerializedName("pageid")
    private int pageid;

    @SerializedName("title")
    private String wikiGeoResultTitle;

    public int getPageid()
    {
        return pageid;
    }

    public String getWikiGeoResultTitle()
    {
        return wikiGeoResultTitle;
    }

}
