package com.example.sunshine.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Danjie on 2/1/2015.
 */
public class Weather {
    @SerializedName("main")
    private String description;

    public String getDescription() { return description; }
}
