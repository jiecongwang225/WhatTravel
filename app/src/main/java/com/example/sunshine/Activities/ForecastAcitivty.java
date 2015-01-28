package com.example.sunshine.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.example.sunshine.Fragment.ForecastFragmentManager;
import com.example.sunshine.R;

/**
 * Created by Danjie on 1/27/2015.
 */
public class ForecastAcitivty extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ForecastFragmentManager forecastFragmentManager = new ForecastFragmentManager(this);
        forecastFragmentManager.initFragment();
    }
}
