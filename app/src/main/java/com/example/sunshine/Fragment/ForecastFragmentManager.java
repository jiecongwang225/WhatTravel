package com.example.sunshine.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.example.sunshine.R;

/**
 * Created by Danjie on 1/26/2015.
 */
public class ForecastFragmentManager {
    private static final String FRAGMENT_TAG = ForecastFragmentManager.class.getSimpleName();
    private ForecastFragment mForecastFragment;
    private final FragmentManager mFragmentManager;
    private final Activity mActivity;
    private final int mContainerId;

    public ForecastFragmentManager(FragmentActivity activity) {
        mActivity =activity;
        mFragmentManager =  activity.getSupportFragmentManager();
        mContainerId = R.id.container;
    }

    public void initFragment() {
        initFragment(null);
    }


    public void initFragment(Bundle bundle) {
        ForecastFragment forecastFragment = (ForecastFragment)mFragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (forecastFragment != null) {
            mForecastFragment = forecastFragment;
        }else {
            mForecastFragment = new ForecastFragment();
        }
        if (bundle !=null)
            mForecastFragment.setArguments(bundle);
        mFragmentManager.beginTransaction().add(mContainerId,mForecastFragment,FRAGMENT_TAG).commit();
    }

    public FragmentManager getFragmentManager() {
        return mFragmentManager;
    }

    public ForecastFragment getForecastFragment() {
        return mForecastFragment;
    }


}
