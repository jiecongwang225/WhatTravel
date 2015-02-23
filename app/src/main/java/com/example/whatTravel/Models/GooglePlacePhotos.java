package com.example.whatTravel.Models;

import com.example.whatTravel.API.WTAPIConstants;

/**
 * Created by jiecongwang on 2/14/15.
 */
public class GooglePlacePhotos extends GooglePlaces {

    @Override
    protected String getOutputType() {
        return "photo";
    }

    @Override
    protected String getSearchType() {
        return null;
    }

    public void setPhotoReferences(String reference) {
        params.put("photoreference",reference);
    }

    public void setMaxWith(int maxWith) {
        params.put("maxwidth",Integer.toString(maxWith));
    }

    public void setMaxHeight(int maxHeight) {
        params.put("maxheight",Integer.toString(maxHeight));
    }

}
