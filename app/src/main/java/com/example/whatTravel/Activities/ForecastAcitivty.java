package com.example.whatTravel.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
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
        getMenuInflater().inflate(R.menu.forecastfragment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            ForecastSearch forecastSearch = new ForecastSearch();
            forecastSearch.setQuery(WTAPIConstants.FORECAST_QUERY_PARAM);
            forecastSearch.setFormat(WTAPIConstants.FORECAST_FORMAT_PARAM);
            forecastSearch.setUnits(WTAPIConstants.FORECAST_UNITS_PARAM);
            forecastSearch.setDays(WTAPIConstants.FORECAST_DAYS_PARAM);
            mloadManager.loadDataFromServer(WTAPIConstants.LOAD_WHEATHER_DATA, forecastSearch);
            return true;
        }
        return super.onOptionsItemSelected(item);
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
    public void onDataLoadFailed(int loaderId, VolleyError error) {
        if (loaderId == WTAPIConstants.LOAD_WHEATHER_DATA) {
            WTLog.error(LOG_TAG, error.toString());
        }
    }
}
