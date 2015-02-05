package com.example.whatTravel.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jiecongwang on 1/25/15.
 */
public class Photo implements Serializable {
    @SerializedName("height")
    private Integer height;

    @SerializedName("html_attributions")
    private List<String> html_attributions;

    @SerializedName("photo_reference")
    private String photo_reference;

    @SerializedName("width")
    private Integer width;

    public  Integer getHeight() {
        return height;
    }

    public List<String> getHtml_attributions() {
        return html_attributions;
    }

    public String getPhoto_reference() {
        return photo_reference;
    }

    public  Integer getWidth() {
        return width;
    }

}
