package com.example.whatTravel.Models.wiki;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dlu on 4/12/15.
 */
public class WikiGeoSearchResults {
    @SerializedName("geosearch")
    private List<WikiGeoSearchResult> wikiGeoSearchResult_list;

    public List<WikiGeoSearchResult> getWikiGeoSearchResult_list()
    {
        return wikiGeoSearchResult_list;
    }
}
