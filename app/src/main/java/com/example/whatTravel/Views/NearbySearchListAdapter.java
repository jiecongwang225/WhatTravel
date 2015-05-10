package com.example.whatTravel.Views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.whatTravel.Models.NearbySearchResult;
import com.example.whatTravel.R;
import com.google.common.collect.Lists;
import com.example.whatTravel.Views.NearbyLocationItemView.ViewHolder;

import java.util.List;

/**
 * Created by jiecongwang on 2/5/15.
 */
public class NearbySearchListAdapter extends RecyclerView.Adapter {
    private final String LOG_TAG = NearbySearchListAdapter.class.getSimpleName();

    private final List<NearbySearchResult> m_NearbySearchResults = Lists.newArrayList();

    public NearbySearchResult getM_NearbySearchResult (int position) {
        return m_NearbySearchResults.get(position);
    }

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
            if (!m_NearbySearchResults.contains(result)) {
                m_NearbySearchResults.add(result);
            }
        }
        notifyDataSetChanged();
    }

    public synchronized void clear() {
        m_NearbySearchResults.clear();
    }



}
