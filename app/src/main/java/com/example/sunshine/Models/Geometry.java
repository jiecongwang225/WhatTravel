package com.example.sunshine.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jiecongwang on 1/25/15.
 */
public class Geometry implements Serializable {

    @SerializedName("location")
    private Location location ;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
