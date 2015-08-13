package com.ysy.testapp;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class MainActivity extends FragmentActivity {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;
    ImageView imageView, imageView2;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageview);

        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
      /*  mDemoCollectionPagerAdapter =
                new DemoCollectionPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);*/

        imageView = (ImageView) findViewById(R.id.imageview);
        imageView2 = (ImageView) findViewById(R.id.imageview2);
        RequestQueue mQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest("http://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.d("TAG", s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("TAG", volleyError.getMessage(), volleyError);
            }
        });
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://m.weather.com.cn/data/101010100.html",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });

        ImageRequest imageRequest = new ImageRequest("http://www.webmaster5u.com/upfiles" +
                "/file/201107/20110723234012108.jpg",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                imageView.setImageResource(R.drawable.ic_launcher);
            }
        });

        ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());

        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(imageView2,
                R.drawable.ic_launcher, R.drawable.ic_launcher);

        imageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",
                imageListener);

        mQueue.add(stringRequest);
        mQueue.add(jsonObjectRequest);
        mQueue.add(imageRequest);
    }
}

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.


// Instances of this class are fragments representing a single
// object in our collection.

