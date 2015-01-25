package com.example.sunshine.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sunshine.Models.NearbySearchResult;
import com.example.sunshine.R;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiecongwang on 1/25/15.
 */
public class NearbySearchFragment extends Fragment {

    private final List<String> weatherData= Lists.newArrayList();
    private ArrayAdapter<String> adapter=null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);

        adapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item_forecast,R.id.list_item_forecast_textview,weatherData);
        ListView listView = (ListView)view.findViewById(R.id.listview_forecast);
        listView.setAdapter(adapter);
        return view;
    }



    public void onSearchResultLoad(List<NearbySearchResult> results){
        weatherData.clear();
        for (NearbySearchResult result:results) {
            weatherData.add(result.getName());
        }
        adapter.notifyDataSetChanged();
    }


}
