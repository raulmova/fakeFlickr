package com.example.raul.flikr;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Raul on 27/08/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    String url;
    String category;
    ArrayList<String> urlsGrid = new ArrayList<String>();
     ArrayList<String> ids = new ArrayList<>();
     ArrayList<String> owner = new ArrayList<>();

    public ImageAdapter(Context c, String category,ArrayList<String> urlsList,ArrayList<String> idsList,ArrayList<String> ownerList) {
        mContext = c;
        this.category = category;
        urlsGrid = urlsList;
        ids =  idsList;
        owner = ownerList;
    }

    public int getCount() {
        return urlsGrid.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(750, 550));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }
        Picasso.with(mContext).load(urlsGrid.get(position)).skipMemoryCache().resize(750,550).into(imageView);
        return imageView;
    }

}