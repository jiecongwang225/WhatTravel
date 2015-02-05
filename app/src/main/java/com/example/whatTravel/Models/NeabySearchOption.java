package com.example.whatTravel.Models;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by jiecongwang on 1/17/15.
 */
public class NeabySearchOption {

    private final List<PlacesType> types = Lists.newArrayList() ;


    private void addType(PlacesType placesType) {
        types.add(placesType);
    }


    private String getPlacesType() {
        return Joiner.on("|").join(types);
    }


}
