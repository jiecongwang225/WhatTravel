package com.example.sunshine.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jiecongwang on 1/25/15.
 */
public class NearbySearchResults {

    @SerializedName("html_attributions")
    private List<String> html_attributions;

    @SerializedName("results")
    private List<NearbySearchResult> results;

    @SerializedName("status")
    private String status;


    public List<String> getHtml_attributions() {
        return html_attributions;
    }

    public List<NearbySearchResult> getResults() {
        return results;
    }

    public String getStatus() {
        return status;
    }

}
