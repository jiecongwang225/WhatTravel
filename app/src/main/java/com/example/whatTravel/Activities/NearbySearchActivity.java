package com.example.whatTravel.Activities;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.VolleyError;
import com.example.whatTravel.API.WTAPIConstants;
import com.example.whatTravel.API.WTApiLoadManager;
import com.example.whatTravel.Fragment.NeabySearchFragmentManager;
import com.example.whatTravel.Models.Coordinate;
import com.example.whatTravel.Models.NearbySearch;
import com.example.whatTravel.Models.NearbySearchResult;
import com.example.whatTravel.Models.NearbySearchResults;
import com.example.whatTravel.Models.SearchTypes;
import com.example.whatTravel.R;
import com.example.whatTravel.helpers.ResponseParser;
import com.example.whatTravel.helpers.WTLocationClient;
import com.example.whatTravel.utils.WTLog;

import java.util.List;


public class NearbySearchActivity extends ActionBarActivity implements WTApiLoadManager.DataLoadLister {

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
        if (currentLocation !=null) {
            WTApiLoadManager loadManager = WTApiLoadManager.getInstance();
            loadManager.setListener(this);
            NearbySearch nearbySearch = new NearbySearch();
            nearbySearch.setCurrentLocation(new Coordinate(currentLocation.getLatitude(),currentLocation.getLatitude()));
            SearchTypes searchTypes = new SearchTypes();
            searchTypes.addType("food");
            nearbySearch.setRadius(10000);
            loadManager.loadDataFromServer(WTAPIConstants.LOAD_NEARBY_PARK, nearbySearch);
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
        if (id == R.id.forecast) {
            Intent forecast = new Intent(NearbySearchActivity.this, ForecastAcitivty.class);
            startActivity(forecast);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDataSuccessLoad(int loaderId, String response) {
        if(loaderId == WTAPIConstants.LOAD_NEARBY_PARK) {
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
