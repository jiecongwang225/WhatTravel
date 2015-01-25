package com.example.sunshine.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jiecongwang on 1/25/15.
 */
public class OpenningHours implements Serializable {

    @SerializedName("open_now")
    private Boolean open_now;

    public boolean isOpenNow() {
        return open_now;
    }

    public void setOpenNow(boolean open_now) {
        this.open_now = open_now;
    }
}
