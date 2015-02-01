package com.example.sunshine.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Danjie on 2/1/2015.
 */
public class ForecastResult implements Serializable {

    @SerializedName("dt")
    private long date_time;

    @SerializedName("temp")
    private Temperature temperature;

    @SerializedName("weather")
    private Weather[] weather;

    public long getDate_time() { return date_time; }
    public Temperature getTemperature() { return temperature; }
    public Weather[] getWeather() { return weather; }

}
