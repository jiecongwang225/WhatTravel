package com.example.whatTravel.API;


import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.whatTravel.Models.WTApiQueries;
import com.example.whatTravel.context.WTApplication;

/**
 * Created by jiecongwang on 1/13/15.
 */
public enum  WTApiLoadManager  {
    INSTANCE;

    public static interface DataLoadLister {

         public void onDataSuccessLoad(int loaderId,String response);

         public void onDataLoadFailed(int loaderId,VolleyError error);
    }


    private RequestQueue mRequestQueue;
    private DataLoadLister mDataLoaderListener;


    private WTApiLoadManager() {
        mRequestQueue = Volley.newRequestQueue(WTApplication.getInstance().getApplicationContext());
    }

    public static WTApiLoadManager getInstance() {
        return INSTANCE;
    }

    public synchronized void setListener(DataLoadLister listener) {
        mDataLoaderListener = listener;

    }

    public void loadDataFromServer(int loaderId,WTApiQueries queries) {
            WTRequest request = new WTRequest(loaderId,mDataLoaderListener,queries.getRequestUrl());
            mRequestQueue.add(request);
    }

    public void stop() {
        mRequestQueue.stop();
    }

    public void start() {
        mRequestQueue.start();
    }


}
