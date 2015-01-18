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
import com.example.sunshine.Models.Coordinate;
import com.example.sunshine.Models.NeabySearchOption;
import com.example.sunshine.Models.NearbySearch;
import com.example.sunshine.R;
import com.example.sunshine.utils.WTLog;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements WTApiLoadManager.DataLoadLister {

    private static final int LOAD_NEARBY_PARK =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        WTApiLoadManager loadManager = WTApiLoadManager.getInstance();
        loadManager.setListener(this);
        NearbySearch nearbySearch = new NearbySearch();
        nearbySearch.setCurrentLocation(new Coordinate(42.3121836,-71.2129264));
        nearbySearch.setRadius(100);
        loadManager.loadDataFromServer(LOAD_NEARBY_PARK,nearbySearch);

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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private  List<String> weatherData=null;
        private  ArrayAdapter<String> adapter=null;


        public PlaceholderFragment() {
           // getActivity will be n

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            weatherData = new ArrayList<String>();
            weatherData.add("Today-Sunny-88/63");
            weatherData.add("Tommorrow-Foggy-70/46");
            weatherData.add("Weds-Cloudy-72/63");
            weatherData.add("Thurs-Rainy-64/51");
            weatherData.add("Fri-Foggy-70/46");
            weatherData.add("Sat-Sunny-76/68");

            adapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item_forecast,R.id.list_item_forecast_textview,weatherData);
            ListView listView = (ListView)rootView.findViewById(R.id.listview_forecast);
            listView.setAdapter(adapter);
            return rootView;
        }
    }


    @Override
    public void onDataSuccessLoad(int loaderId, String response) {
        if(loaderId == LOAD_NEARBY_PARK) {
            WTLog.debug("test", response);
        }
    }

    @Override
    public void onDataLoadFailed(int loaderId, VolleyError error) {

    }
}
