package com.example.whatTravel.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.VolleyError;
import com.example.whatTravel.API.WTAPIConstants;
import com.example.whatTravel.API.WTApiLoadManager;
import com.example.whatTravel.Fragment.ForecastFragmentManager;
import com.example.whatTravel.Models.ForecastResults;
import com.example.whatTravel.Models.ForecastSearch;
import com.example.whatTravel.R;
import com.example.whatTravel.helpers.ResponseParser;
import com.example.whatTravel.utils.WTLog;

/**
 * Created by Danjie on 1/27/2015.
 */
public class ForecastAcitivty extends ActionBarActivity implements WTApiLoadManager.DataLoadLister{

    private final String LOG_TAG = ForecastAcitivty.class.getSimpleName();
    private WTApiLoadManager mloadManager = WTApiLoadManager.getInstance();

    public Activity getActivity() {
        return this;
    }

    private ForecastFragmentManager mForecastFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mForecastFragmentManager = new ForecastFragmentManager(this);
        mForecastFragmentManager.initFragment();
        mloadManager.setListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater manueflater = getMenuInflater();
        manueflater.inflate(R.menu.forecastfragment, menu);
        manueflater.inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            updateWeather();
            return true;
        }
        if(id == R.id.forecast_action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        if (id == R.id.action_map) {
            openPreferredLocationInMap();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateWeather() {
        ForecastSearch forecastSearch = new ForecastSearch();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String location = prefs.getString(getString(R.string.pref_location_key),
                getString(R.string.pref_location_default));
        String unitsType = prefs.getString(getString(R.string.pref_units_key),
                getString(R.string.pref_units_metric));
        WTLog.debug(LOG_TAG, "Forecast location: " + location);
        WTLog.debug(LOG_TAG, "Forecast unitsType: " + unitsType);
        forecastSearch.setQuery(location);
        forecastSearch.setFormat(WTAPIConstants.JSON);
        forecastSearch.setUnits(unitsType);
        forecastSearch.setDays(WTAPIConstants.FORECAST_DAYS_PARAM);
        mloadManager.loadDataFromServer(WTAPIConstants.LOAD_WHEATHER_DATA, forecastSearch);
    }

    @Override
    public void onDataSuccessLoad(int loaderId, String response) {
        if(loaderId == WTAPIConstants.LOAD_WHEATHER_DATA) {
            ForecastResults forecastResults = ResponseParser.getForecastResults(response);
            if(forecastResults != null){
                WTLog.debug("parser....", forecastResults.toString());
                if("200".equals(forecastResults.getStatus())) {
                    mForecastFragmentManager.getForecastFragment().onForecastResultLoad(forecastResults.getForecast_lists());
                }
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mloadManager.start();
        updateWeather();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mloadManager.stop();

    }

    @Override
    public void onDataLoadFailed(int loaderId, VolleyError error) {
        if (loaderId == WTAPIConstants.LOAD_WHEATHER_DATA) {
            WTLog.error(LOG_TAG, error.toString());
        }
    }

    private void openPreferredLocationInMap() {
        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(this);
        String location = sharedPrefs.getString(
                getString(R.string.pref_location_key),
                getString(R.string.pref_location_default)
        );

        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q", location)
                .build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else {
            WTLog.debug(LOG_TAG, "Couldn't call " + location);
        }

    }
}
