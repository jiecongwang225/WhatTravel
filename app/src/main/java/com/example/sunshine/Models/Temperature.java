package com.example.sunshine.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Danjie on 2/1/2015.
 */
public class Temperature {

    @SerializedName("day")
    private double day_temp;

    @SerializedName("min")
    private double min_temp;

    @SerializedName("max")
    private double max_temp;

    @SerializedName("night")
    private double night_temp;

    @SerializedName("eve")
    private double eve_temp;

    @SerializedName("morn")
    private double morn_temp;

    public double getDay_temp() { return day_temp; }
    public double getMin_temp() { return min_temp; }
    public double getMax_temp() { return max_temp; }
    public double getNight_temp() { return night_temp; }
    public double getEve_temp() { return eve_temp; }
    public double getMorn_temp() { return morn_temp; }

}
