package com.example.whatTravel.API;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;

/**
 * Created by jiecongwang on 1/13/15.
 */
public class WTRequest extends Request<String> {

    private final WTApiLoadManager.DataLoadLister mlistener;
    private final int loaderId;
    public WTRequest(int loaderId,WTApiLoadManager.DataLoadLister listener,String url) {
        super(Method.GET,url,null);
        mlistener = listener;
        this.loaderId = loaderId;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response,true));
    }

    @Override
    protected void deliverResponse(String response) {
        if (mlistener!=null) {
            mlistener.onDataSuccessLoad(loaderId, response);
        }
    }

    @Override
    public void deliverError(VolleyError error) {
        if (mlistener !=null) {
            mlistener.onDataLoadFailed(loaderId,error);
        }
    }
}
