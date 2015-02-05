package com.example.whatTravel.Models;

/**
 * Created by jiecongwang on 1/17/15.
 */
public enum PlacesType {

    rv_park("rv_park");

    private String type;
    private PlacesType(String type) {
        this.type = type;
    }

    public String toString() {
        return type;
    }


}
