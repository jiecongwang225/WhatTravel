package com.example.sunshine.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jiecongwang on 1/25/15.
 */
public class AltId implements Serializable {

    @SerializedName("place_id")
    private String place_id;

    @SerializedName("scope")
    private String scope;


    public String getPlace_id() {
        return place_id;
    }

    public String getScope() {
        return scope;
    }


}
