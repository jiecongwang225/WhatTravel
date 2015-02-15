package com.example.whatTravel.Views;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.whatTravel.Models.GooglePlacePhotos;
import com.example.whatTravel.Models.GooglePlaces;
import com.example.whatTravel.Models.NearbySearchResult;
import com.example.whatTravel.Models.Photo;
import com.example.whatTravel.R;
import com.example.whatTravel.helpers.ImageHelper;
import com.example.whatTravel.utils.Utils;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by jiecongwang on 2/14/15.
 */
public class NearbyLocationItemView extends RelativeLayout {

    public NearbyLocationItemView(Context context) {
        super(context);
    }

    public NearbyLocationItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTitle;
        TextView mRating;
        TextView mVicinity;
        NetworkImageView mImageView;

        public ViewHolder(NearbyLocationItemView view) {
            super(view);
            mTitle = (TextView)view.findViewById(R.id.title);
            mRating =(TextView)view.findViewById(R.id.ranking);
            mVicinity =(TextView)view.findViewById(R.id.vicinity);
            mImageView =(NetworkImageView)view.findViewById(R.id.thumbnail);
        }
    }

    public static void setNearbyResultForViewHolder(ViewHolder viewHolder,NearbySearchResult result) {
        List<Photo> photos =  result.getPhotos();
        if (!Utils.isEmptySafe(photos)) {
            Photo thumbnail = photos.get(0);
            GooglePlacePhotos placePhotos = new GooglePlacePhotos();
            placePhotos.setPhotoReferences(thumbnail.getPhoto_reference());
            placePhotos.setMaxWith(96);
            viewHolder.mImageView.setImageUrl(placePhotos.getRequestUrl(), ImageHelper.getInstance().getImageLoader());
        }
        viewHolder.mTitle.setText(result.getName());
        String rating = result.getRating()==null?"0/5":result.getRating()+"/5";
        viewHolder.mRating.setText(rating);
        viewHolder.mVicinity.setText(result.getVicinity());
    }


}
