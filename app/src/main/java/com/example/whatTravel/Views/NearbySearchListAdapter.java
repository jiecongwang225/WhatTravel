package com.example.whatTravel.Views;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.android.volley.toolbox.ImageLoader;
import com.example.whatTravel.API.WTApiLoadManager;
import com.example.whatTravel.Models.NearbySearchResult;
import com.example.whatTravel.R;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.example.whatTravel.Views.NearbyLocationItemView.ViewHolder;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Set;

/**
 * Created by jiecongwang on 2/5/15.
 */
public class NearbySearchListAdapter extends RecyclerView.Adapter {


    private final Set<String> ids = Sets.newHashSet();

    private final List<NearbySearchResult> m_NearbySearchResults = Lists.newArrayList();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NearbyLocationItemView layout = (NearbyLocationItemView)LayoutInflater.from(parent.getContext()).inflate(R.layout.nearby_list_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(layout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NearbySearchResult nearbySearchResult = m_NearbySearchResults.get(position);
        NearbyLocationItemView.setNearbyResultForViewHolder((ViewHolder)holder,nearbySearchResult);
    }

    @Override
    public int getItemCount() {
        return m_NearbySearchResults.size();
    }

    public synchronized void reloadData(List<NearbySearchResult> results) {
        for (NearbySearchResult result :results) {
            if (!ids.contains(result.getId())) {
                ids.add(result.getId());
                m_NearbySearchResults.add(result);
            }
        }
        notifyDataSetChanged();

    }

    public synchronized void clear() {
        ids.clear();
        m_NearbySearchResults.clear();
    }



}
