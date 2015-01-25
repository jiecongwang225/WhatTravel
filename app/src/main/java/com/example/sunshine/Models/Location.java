package com.example.sunshine.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jiecongwang on 1/25/15.
 */
public class Location implements Serializable {

    @SerializedName("lat")
    private Double lat;

    @SerializedName("lng")
    private Double lng;

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLat(double lat) {
        this.lat =lat;
    }

    public void setLng(double lng) {
        this.lng =lng;
    }
}
