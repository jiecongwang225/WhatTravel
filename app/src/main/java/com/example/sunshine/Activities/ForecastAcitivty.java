package com.example.sunshine.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;

import com.android.volley.VolleyError;
import com.example.sunshine.API.WTAPIConstants;
import com.example.sunshine.API.WTApiLoadManager;
import com.example.sunshine.Fragment.ForecastFragment;
import com.example.sunshine.Fragment.ForecastFragmentManager;
import com.example.sunshine.Models.ForecastResults;
import com.example.sunshine.Models.ForecastSearch;
import com.example.sunshine.R;
import com.example.sunshine.helpers.ResponseParser;
import com.example.sunshine.utils.WTLog;

/**
 * Created by Danjie on 1/27/2015.
 */
public class ForecastAcitivty extends ActionBarActivity implements WTApiLoadManager.DataLoadLister{
    private final String LOG_TAG = ForecastAcitivty.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ForecastFragmentManager forecastFragmentManager = new ForecastFragmentManager(this);
        forecastFragmentManager.initFragment();
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
    public void onDataSuccessLoad(int loaderId, String response) {
        if(loaderId == WTAPIConstants.LOAD_WHEATHER_DATA) {
            ForecastResults forecastResults = ResponseParser.getForecastResults(response);
            WTLog.debug("parser....", forecastResults.toString());
            ForecastFragment.onForecastResultLoad(forecastResults.getForecast_lists());
        }
    }

    @Override
    public void onDataLoadFailed(int loaderId, VolleyError error) {
        if (loaderId == WTAPIConstants.LOAD_WHEATHER_DATA) {
            WTLog.error(LOG_TAG, error.toString());
        }
    }
}
