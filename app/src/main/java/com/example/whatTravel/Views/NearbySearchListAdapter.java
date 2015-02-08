package com.example.whatTravel.Views;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.whatTravel.API.WTApiLoadManager;
import com.example.whatTravel.Models.NearbySearchResult;
import com.example.whatTravel.R;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Set;

/**
 * Created by jiecongwang on 2/5/15.
 */
public class NearbySearchListAdapter extends RecyclerView.Adapter {


    private final Set<String> ids = Sets.newHashSet();

    private final List<NearbySearchResult> m_NearbySearchResults = Lists.newArrayList();
    private final ImageLoader  mImageLoader = new ImageLoader(WTApiLoadManager.getInstance().getRequestQueue(),
                                                               new ImageLoader.ImageCache() {
        private final LruCache<String, Bitmap>
                cache = new LruCache<String, Bitmap>(20);

        @Override
        public Bitmap getBitmap(String url) {
            return cache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            cache.put(url, bitmap);
        }
    });

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        NetworkImageView mImageView;

        public ViewHolder(LinearLayout linearLayout) {
             super(linearLayout);
             mTextView =(TextView)linearLayout.findViewById(R.id.nearby_list_title);
             mImageView = (NetworkImageView)linearLayout.findViewById(R.id.nearby_list_thumbnail);

        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout layout = (LinearLayout)LayoutInflater.from(parent.getContext()).inflate(R.layout.nearby_list_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(layout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       NearbySearchResult nearbySearchResult = m_NearbySearchResults.get(position);
       ViewHolder viewHolder = (ViewHolder)holder;
       viewHolder.mTextView.setText(nearbySearchResult.getName());
       viewHolder.mImageView.setImageUrl(nearbySearchResult.getIconUrl(),mImageLoader);
    }

    @Override
    public int getItemCount() {
        return m_NearbySearchResults.size();
    }

    public void reloadData(List<NearbySearchResult> results) {
         for (NearbySearchResult result :results) {
             if (!ids.contains(result.getId())) {
                 ids.add(result.getId());
                 m_NearbySearchResults.add(result);
             }
         }
         notifyDataSetChanged();

    }

    public void clear() {
        ids.clear();
        m_NearbySearchResults.clear();
    }



}
