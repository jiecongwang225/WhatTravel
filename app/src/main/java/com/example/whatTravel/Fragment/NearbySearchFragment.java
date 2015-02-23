package com.example.whatTravel.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whatTravel.Models.NearbySearchResult;
import com.example.whatTravel.R;
import com.example.whatTravel.Views.NearbySearchListAdapter;

import java.util.List;

/**
 * Created by jiecongwang on 1/25/15.
 */
public class NearbySearchFragment extends Fragment {

    private RecyclerView mRecycleView;
    private NearbySearchListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String token;
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
                super.onScrolled(recyclerView,dx,dy);
                int lastVisableItem = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
                int totalItem = mLayoutManager.getItemCount();
                if (lastVisableItem >= totalItem && dy >0) {  // when recycleview ,recycle to the bottom, try to load more.
                    mCallbacks.loadMore();
                }
             }
        });
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
