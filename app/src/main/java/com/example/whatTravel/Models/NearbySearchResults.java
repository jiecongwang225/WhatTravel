package com.example.whatTravel.Models;

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

    @SerializedName("next_page_token")
    private String next_token;


    public List<String> getHtml_attributions() {
        return html_attributions;
    }

    public List<NearbySearchResult> getResults() {
        return results;
    }

    public String getStatus() {
        return status;
    }

    public String getNextToken() {
        return next_token;
    }

}
