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

import com.example.sunshine.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiecongwang on 1/25/15.
 */
public class NearbySearchFragment extends Fragment {

    private  List<String> weatherData=null;
    private ArrayAdapter<String> adapter=null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        weatherData = new ArrayList<String>();
        weatherData.add("Today-Sunny-88/63");
        weatherData.add("Tommorrow-Foggy-70/46");
        weatherData.add("Weds-Cloudy-72/63");
        weatherData.add("Thurs-Rainy-64/51");
        weatherData.add("Fri-Foggy-70/46");
        weatherData.add("Sat-Sunny-76/68");
        adapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item_forecast,R.id.list_item_forecast_textview,weatherData);
        ListView listView = (ListView)view.findViewById(R.id.listview_forecast);
        listView.setAdapter(adapter);
        return view;
    }



    public void onSearchResultLoad(List<Object> results){


    }


}
