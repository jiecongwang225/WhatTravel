package com.example.sunshine.Activities;

import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.sunshine.API.WTApiLoadManager;
import com.example.sunshine.Fragment.NeabySearchFragmentManager;
import com.example.sunshine.Fragment.NearbySearchFragment;
import com.example.sunshine.Models.Coordinate;
import com.example.sunshine.Models.NeabySearchOption;
import com.example.sunshine.Models.NearbySearch;
import com.example.sunshine.Models.NearbySearchResult;
import com.example.sunshine.Models.NearbySearchResults;
import com.example.sunshine.Models.SearchTypes;
import com.example.sunshine.R;
import com.example.sunshine.helpers.ResponseParser;
import com.example.sunshine.helpers.WTLocationClient;
import com.example.sunshine.utils.WTLog;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements WTApiLoadManager.DataLoadLister {

    private static final int LOAD_NEARBY_PARK =1;
    private NeabySearchFragmentManager mNearbySearchFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNearbySearchFragmentManager= new NeabySearchFragmentManager(this);
        mNearbySearchFragmentManager.initFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Location currentLocation = WTLocationClient.getInstance().getLastKnowLocation();
        WTLog.debug("Location", currentLocation.toString());
        if (currentLocation !=null) {
            WTApiLoadManager loadManager = WTApiLoadManager.getInstance();
            loadManager.setListener(this);
            NearbySearch nearbySearch = new NearbySearch();
            nearbySearch.setCurrentLocation(new Coordinate(currentLocation.getLatitude(),currentLocation.getLatitude()));
            SearchTypes searchTypes = new SearchTypes();
            searchTypes.addType("food");
            nearbySearch.setRadius(10000);
            loadManager.loadDataFromServer(LOAD_NEARBY_PARK, nearbySearch);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDataSuccessLoad(int loaderId, String response) {
        if(loaderId == LOAD_NEARBY_PARK) {
           NearbySearchResults nearbySearchResults = ResponseParser.getNearbySearchResults(response);
           WTLog.debug("parser....",nearbySearchResults.toString());
           if (nearbySearchResults !=null) {
              String status = nearbySearchResults.getStatus();
              if ("OK".equalsIgnoreCase(status)) {
                  List<NearbySearchResult> results = nearbySearchResults.getResults();
                  mNearbySearchFragmentManager.getNearbySearchFragment().onSearchResultLoad(results);

              }
           }
        }
    }

    @Override
    public void onDataLoadFailed(int loaderId, VolleyError error) {

    }

}
