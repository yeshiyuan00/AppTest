package com.ysy.testapp;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by ysy on 2015/4/17.
 */
public class BitmapCache implements ImageLoader.ImageCache {
    private LruCache<String,Bitmap> mCache;

    public BitmapCache(){
        int maxSize = 10 * 1024 * 1024;
        mCache=new LruCache<String,Bitmap>(maxSize){

            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String s) {
        return null;
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {

    }
}
