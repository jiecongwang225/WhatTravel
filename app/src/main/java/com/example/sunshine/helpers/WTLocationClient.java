package com.example.sunshine.helpers;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.example.sunshine.context.WTApplication;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author jiecongwang
 */
public class WTLocationClient implements LocationListener {

    private final LocationManager mLocationManager;
    private final Context mContext;
    private final ArrayList<WTLocationListener> mListeners ;
    private final static int DURATION =1000;


    private static class SingletonHolder {
        private final static WTLocationClient INSTANCE = new WTLocationClient();
    }

    public static WTLocationClient getInstance() {
        return SingletonHolder.INSTANCE;
    }


    private WTLocationClient() {
        mListeners = Lists.newArrayList();
        mContext = WTApplication.getInstance().getApplicationContext();
        mLocationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        for (String providerName : providers) {
            if (LocationManager.GPS_PROVIDER.equalsIgnoreCase(providerName)) {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,DURATION,0,this);
            }else if (LocationManager.NETWORK_PROVIDER.equalsIgnoreCase(providerName)) {
                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,DURATION,0,this);
            }
        }
    }


    public interface WTLocationListener {
        public void onLocationUpdate(Location location);
    }


    public void registerListener(WTLocationListener locationListener) {
        mListeners.add(locationListener);
    }

    public void unRegisterListener(WTLocationListener locationListener) {
        mListeners.remove(locationListener);
    }


    @Override
    public void onLocationChanged(Location location) {
        if (location !=null) {
            for (WTLocationListener listener : mListeners) {
                listener.onLocationUpdate(location);
            }
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    public Location getLastKnowLocation()  {
        return getLastKnownLocation(mLocationManager,mLocationManager.getProviders(true));
    }


    private Location getLastKnownLocation(LocationManager locationManager, List<String> providers) {
        TreeMap<Long, Location> locations = new TreeMap<Long, Location>(Collections.reverseOrder());
        for (String provider : providers) {
            try {
                Location l = locationManager.getLastKnownLocation(provider);
                if (l != null)
                    locations.put(l.getTime(), l);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (Map.Entry<Long, Location> map : locations.entrySet()) {
            Location location = map.getValue();
            return location;
        }

        return null;
    }
}
