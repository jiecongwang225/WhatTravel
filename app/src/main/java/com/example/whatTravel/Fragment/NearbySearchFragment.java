package com.example.whatTravel.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whatTravel.API.WTAPIConstants;
import com.example.whatTravel.Activities.WikiDetailActivity;
import com.example.whatTravel.Models.Geometry;
import com.example.whatTravel.Models.Location;
import com.example.whatTravel.Models.NearbySearchResult;
import com.example.whatTravel.Models.RecyclerItemClickListener;
import com.example.whatTravel.R;
import com.example.whatTravel.Views.NearbySearchListAdapter;
import com.example.whatTravel.utils.WTLog;

import java.util.List;

/**
 * Created by jiecongwang on 1/25/15.
 */
public class NearbySearchFragment extends Fragment {

    private RecyclerView mRecycleView;
    private NearbySearchListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String token;
    private final String LOG_TAG = NearbySearchFragment.class.getSimpleName();
    private NeabySearchFragmentManager.NearbySearchFragmentCallBacks mCallbacks;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            mCallbacks =(NeabySearchFragmentManager.NearbySearchFragmentCallBacks) activity;
        }catch (ClassCastException e) {
            throw  new ClassCastException("the activity must implemented the NearbySearchFragmentCallBacks"+e);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearbysearch,container,false);
        mRecycleView = (RecyclerView)view.findViewById(R.id.nearby_search_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mRecycleView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisableItem = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
                int totalItem = mLayoutManager.getItemCount();
                if (lastVisableItem >= totalItem && dy > 0) {  // when recycleview ,recycle to the bottom, try to load more.
                    mCallbacks.loadMore();
                }
            }
        });

        mRecycleView.addOnItemTouchListener(
            new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    String extraText = "";
                    NearbySearchResult item = mAdapter.getM_NearbySearchResult(position);
                    if (WTAPIConstants.WIKI_GEO_SEARCH_ENABLED) {
                        Location location = item.getGeometry().getLocation();
                        extraText = item.getName() + WTAPIConstants.SPLIT_TOKEN +
                                location.getLat() + "|" + location.getLng();
                        WTLog.debug(LOG_TAG, extraText);
                    }
                    else
                    {
                        extraText = item.getName();
                    }
                    Intent intent = new Intent(getActivity(), WikiDetailActivity.class).putExtra(Intent.EXTRA_TEXT, extraText);
                    startActivity(intent);
                }
            })
        );
        mAdapter = new NearbySearchListAdapter();
        mRecycleView.setAdapter(mAdapter);

        return view;
    }

    public void onSearchResultLoad(List<NearbySearchResult> results){
        mAdapter.reloadData(results);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
    }
}
