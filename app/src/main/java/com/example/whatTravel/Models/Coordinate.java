package com.example.whatTravel.Models;

/**
 * Created by jiecongwang on 1/13/15.
 */
public class Coordinate {
    private double latitude;
    private double longtude;

    public Coordinate(double latitude,double longtude) {
        this.latitude = latitude;
        this.longtude = longtude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongtude() {
        return longtude;
    }

    public String toString() {
        return latitude+","+longtude;
    }

}
