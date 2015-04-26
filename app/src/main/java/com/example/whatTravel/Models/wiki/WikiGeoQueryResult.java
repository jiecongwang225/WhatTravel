package com.example.whatTravel.Models.wiki;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dlu on 4/12/15.
 */
public class WikiGeoQueryResult {
    @SerializedName("query")
    private WikiGeoSearchResults wikiGeoSearchResults;

    public WikiGeoSearchResults getWikiGeoQueryResult()
    {
        return wikiGeoSearchResults;
    }
}
