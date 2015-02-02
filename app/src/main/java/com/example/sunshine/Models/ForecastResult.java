package com.example.sunshine.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Override
    public String toString() {
        String day = getReadableDateString(getDate_time());
        String description = getWeather()[0].getDescription();
        Temperature temp = getTemperature();
        String highAndLow = formatHighLows(temp.getMax_temp(), temp.getMax_temp());
        return day + " - " + description + " - " + highAndLow;
    }

    private  String getReadableDateString(long time) {
        // Because the API returns a unix timestamp (measured in seconds),
        // it must be converted to milliseconds in order to be converted to valid date.
        Date date = new Date(time * 1000);
        SimpleDateFormat format = new SimpleDateFormat("E, MMM d");
        return format.format(date).toString();
    }

    private  String formatHighLows(double high, double low) {
        // For presentation, assume the user doesn't care about tenths of a degree.
        long roundedHigh = Math.round(high);
        long roundedLow = Math.round(low);

        String highLowStr = roundedHigh + "/" + roundedLow;
        return highLowStr;
    }

}
