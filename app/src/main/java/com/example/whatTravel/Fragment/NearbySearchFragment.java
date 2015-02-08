package com.example.whatTravel.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.whatTravel.Models.NearbySearchResult;
import com.example.whatTravel.R;
import com.example.whatTravel.Views.NearbySearchListAdapter;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by jiecongwang on 1/25/15.
 */
public class NearbySearchFragment extends Fragment {

    private RecyclerView mRecycleView;
    private NearbySearchListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearbysearch,container,false);
        mRecycleView = (RecyclerView)view.findViewById(R.id.nearby_search_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(mLayoutManager);
        mAdapter = new NearbySearchListAdapter();
        mRecycleView.setAdapter(mAdapter);
        return view;
    }



    public void onSearchResultLoad(List<NearbySearchResult> results){
        mAdapter.reloadData(results);
    }


}
