package com.example.whatTravel.helpers;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;
import com.example.whatTravel.API.WTApiLoadManager;

/**
 * Created by jiecongwang on 2/14/15.
 */
public enum  ImageHelper {
    INSTANCE;

    private final ImageLoader imageLoader;
    private ImageHelper() {
        imageLoader = new ImageLoader(WTApiLoadManager.getInstance().getRequestQueue(),
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });

    }

    public static ImageHelper getInstance() {
        return INSTANCE;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

}
