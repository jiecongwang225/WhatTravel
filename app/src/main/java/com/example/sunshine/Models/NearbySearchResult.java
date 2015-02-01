package com.example.sunshine.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jiecongwang on 1/25/15.
 */
public class NearbySearchResult implements Serializable {

    @SerializedName("geometry")
    private Geometry geometry ;

    @SerializedName("icon")
    private String icon;

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("open_hours")
    private OpenningHours opening_hours;

    @SerializedName("photos")
    private List<Photo> photos;

    @SerializedName("place_id")
    private String place_id;

    @SerializedName("scope")
    private String scope;

    @SerializedName("reference")
    private String reference;


    @SerializedName("alt_ids")
    private List<AltId> altIds;

    @SerializedName("types")
    private List<String> types;

    @SerializedName("vicinity")
    private String vicinity;


    public Geometry getGeometry() {
        return geometry;
    }

    public String getIconUrl() {
        return icon;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public OpenningHours getOpening_hours() {
        return opening_hours;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public String getScope() {
        return scope;
    }

    public String getReference() {
        return reference;
    }

    public List<AltId> getAltIds() {
        return altIds;
    }

    public List<String> getTypes() {
        return types;
    }

    public String getVicinity() {
        return vicinity;
    }

}
