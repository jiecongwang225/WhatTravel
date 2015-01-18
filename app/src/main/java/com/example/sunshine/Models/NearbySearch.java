package com.example.sunshine.Models;

import android.location.Location;

import com.example.sunshine.API.WTAPIConstants;

/**
 * Created by jiecongwang on 1/13/15.
 */
public class NearbySearch extends GooglePlaces {

    private static final String SEARCH_TYPE = "nearbysearch";

    private static final String APIKEY = "key";

    private static final String LOCATION = "location";

    private static final String RADIUS ="radius";


    private Coordinate location;

    private int radius;

    public  NearbySearch() {
      super();
      params.put(APIKEY, WTAPIConstants.GOOGLE_API_KEY);
    }

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


    @Override
    protected String getSearchType() {
        return SEARCH_TYPE;
    }

}
