package com.example.sunshine.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Danjie on 2/1/2015.
 */
public class ForecastResults {
    @SerializedName("list")
    private List<ForecastResult> forecast_lists ;

    @SerializedName("cod")
    private String status;

    public List<ForecastResult> getForecast_lists() { return forecast_lists; }
    public String getStatus() { return status; }


}
