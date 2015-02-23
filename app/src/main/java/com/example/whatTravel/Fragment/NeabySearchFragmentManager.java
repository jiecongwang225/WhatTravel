package com.example.whatTravel.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.example.whatTravel.R;

/**
 * Created by jiecongwang on 1/25/15.
 */
public class NeabySearchFragmentManager {

    private static final String FRAGMENT_TAG = "NearbySearchFragment";
    private NearbySearchFragment mNearbySearchFragment;
    private final FragmentManager mFragmentManager;
    private final Activity mActivity;
    private final int mContainerId;

    public interface NearbySearchFragmentCallBacks {
        public void loadMore();
    }


    public NeabySearchFragmentManager(FragmentActivity activity) {
        mActivity =activity;
        mFragmentManager =  activity.getSupportFragmentManager();
        mContainerId = R.id.container;
    }

    public void initFragment() {
        initFragment(null);
    }


    public void initFragment(Bundle bundle) {
        NearbySearchFragment nearbySearchFragment = (NearbySearchFragment)mFragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (nearbySearchFragment !=null) {
            mNearbySearchFragment =nearbySearchFragment;
        }else {
            mNearbySearchFragment = new NearbySearchFragment();
        }
        if (bundle !=null)
            mNearbySearchFragment.setArguments(bundle);
        mFragmentManager.beginTransaction().add(mContainerId,mNearbySearchFragment,FRAGMENT_TAG).commit();
    }

    public FragmentManager getFragmentManager() {
        return mFragmentManager;
    }

    public NearbySearchFragment getNearbySearchFragment() {
        return mNearbySearchFragment;
    }

}
