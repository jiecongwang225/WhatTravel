package com.example.whatTravel.Models;

import android.location.Location;

import com.example.whatTravel.API.WTAPIConstants;

/**
 * Created by jiecongwang on 1/13/15.
 */
public class NearbySearch extends GooglePlaces {

    private static final String SEARCH_TYPE = "nearbysearch";

    private static final String LOCATION = "location";

    private static final String RADIUS ="radius";

    private static final String TYPES ="types";

    private SearchTypes types;


    private Coordinate location;

    private int radius;


    public void setCurrentLocation(Location nearby) {
        location = new Coordinate(nearby.getLatitude(),nearby.getLatitude());
        params.put(LOCATION,location.toString());
    }

    public void setCurrentLocation(Coordinate location) {
        params.put(LOCATION,location.toString());
    }

    public void setRadius(int miles) {
        radius = miles;
        params.put(RADIUS,String.valueOf(miles));
    }

    public void setSearchType(SearchTypes types) {
        this.types =types;
        params.put(TYPES,types.toString());

    }

    public void setSearchType(String types) {
        params.put(TYPES,types);
    }

    public void loadNextPage(String token) {
        params.put("pagetoken",token);
    }

    @Override
    protected String getSearchType() {
        return SEARCH_TYPE;
    }

}
