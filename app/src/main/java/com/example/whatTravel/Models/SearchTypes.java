package com.example.whatTravel.Models;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by jiecongwang on 1/25/15.
 */
public enum  SearchTypes  {
    airport("airport"),
    amusement_park("amusement_park"),
    aquariumM("aquarium"),
    art_gallery("art_gallery"),
    casino("casino"),
    church("church"),
    city_hall("city_hall"),
    museum("museum"),
    park("park"),
    parking("parking"),
    shopping_mall("shopping_mall"),
    subway_stationN("subway_station"),
    train_statio("train_station"),
    university("university"),
    zoo("zoo");

    private String mSearchType;
    private SearchTypes(String searchType) {
        mSearchType = searchType;
    }

    public static String getAllSearchTypes() {
        return Joiner.on("|").join(SearchTypes.values());
    }


}
