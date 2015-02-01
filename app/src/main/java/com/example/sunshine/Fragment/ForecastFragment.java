package com.example.sunshine.Fragment;

/**
 * Created by Danjie on 1/26/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.sunshine.API.WTAPIConstants;
import com.example.sunshine.API.WTApiLoadManager;
import com.example.sunshine.Models.ForecastSearch;
import com.example.sunshine.R;
import com.example.sunshine.helpers.FetchWeatherTask;
import com.example.sunshine.utils.WTLog;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Encapsulates fetching the forecast and displaying it as a {@link ListView} layout.
 */
public class ForecastFragment extends Fragment implements WTApiLoadManager.DataLoadLister{
    private ArrayAdapter<String> mForecastAdapter;
    private final String LOG_TAG = ForecastFragment.class.getSimpleName();

    public ForecastFragment() {
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        WTApiLoadManager loadManager = WTApiLoadManager.getInstance();
        loadManager.setListener(this);
        if (id == R.id.action_refresh) {
            loadManager.setListener(this);
            ForecastSearch forecastSearch = new ForecastSearch();
            forecastSearch.setQuery(WTAPIConstants.FORECAST_QUERY_PARAM);
            forecastSearch.setFormat(WTAPIConstants.FORECAST_FORMAT_PARAM);
            forecastSearch.setUnits(WTAPIConstants.FORECAST_UNITS_PARAM);
            forecastSearch.setDays(WTAPIConstants.FORECAST_DAYS_PARAM);
            loadManager.loadDataFromServer(WTAPIConstants.LOAD_WHEATHER_DATA, forecastSearch);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Create some dummy data for the ListView.  Here's a sample weekly forecast
        String[] data = {
                "Mon 6/23 - Sunny - 31/17",
                "Tue 6/24 - Foggy - 21/8",
                "Wed 6/25 - Cloudy - 22/17",
                "Thurs 6/26 - Rainy - 18/11",
                "Fri 6/27 - Foggy - 21/10",
                "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
                "Sun 6/29 - Sunny - 20/7"
        };
        List<String> weekForecast = new ArrayList<String>(Arrays.asList(data));


        // Now that we have some dummy forecast data, create an ArrayAdapter.
        // The ArrayAdapter will take data from a source (like our dummy forecast) and
        // use it to populate the ListView it's attached to.
        mForecastAdapter =
                new ArrayAdapter<String>(
                        getActivity(), // The current context (this activity)
                        R.layout.list_item_forecast, // The name of the layout ID.
                        R.id.list_item_forecast_textview, // The ID of the textview to populate.
                        weekForecast);

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // Get a reference to the ListView, and attach this adapter to it.
        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(mForecastAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

            }
        });
        return rootView;
    }

    @Override
    public void onDataSuccessLoad(int loaderId, String response) {
        if(loaderId == WTAPIConstants.LOAD_WHEATHER_DATA) {
            String[] result = null;
            try {
                 result = FetchWeatherTask.getWeatherDataFromJson(response, WTAPIConstants.FORECAST_DAYS_PARAM);
            } catch (JSONException e) {
                e.printStackTrace();
                WTLog.error(LOG_TAG, "Fail to parse json String " + e);
            }
            WTLog.debug(LOG_TAG, response);
            if (result != null) {
                mForecastAdapter.clear();
                for (String dayForecastStr : result) {
                    mForecastAdapter.add(dayForecastStr);
                }
            }
        }
    }

    @Override
    public void onDataLoadFailed(int loaderId, VolleyError error) {
        if(loaderId == WTAPIConstants.LOAD_WHEATHER_DATA) {
            Log.v(LOG_TAG, error.toString());
        }
}
}
