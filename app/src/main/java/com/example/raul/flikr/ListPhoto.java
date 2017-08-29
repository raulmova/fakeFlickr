package com.example.raul.flikr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

/*
https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=9b14be59a7d7007b1fae0ea005dc51af&accuracy=1&tags=dog&sort=relevance&extras=url_l&format=json&per_page=10
* */

public class ListPhoto extends AppCompatActivity {

    private String category;
    private String photo;
    private GridView imageGrid;
    private ArrayList<Bitmap> bitmapList;
    private String url;
    ArrayList<String> urlsGrid = new ArrayList<String>();
    ArrayList<String> idsGrid = new ArrayList<String>();
    ArrayList<String> ownersGrid = new ArrayList<String>();
    ImageLoader mImageLoader;
    NetworkImageView mNetworkImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_photo);

        category = getIntent().getExtras().getString("Category");
        photo = getIntent().getExtras().getString("Photo");
       // urlsGrid = getIntent().getStringArrayListExtra("Urls");

        final GridView gridview = (GridView) findViewById(R.id.gridView);
        /////////////////7API CALL
        String url = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=9b14be59a7d7007b1fae0ea005dc51af&accuracy=1&tags=" + category + "&sort=relevance&extras=url_l&format=json&per_page=14";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            response = response.substring(14, response.length() - 1);
                            JSONObject jObj = new JSONObject(response);
                            JSONObject JSONPhotos = jObj.getJSONObject("photos");
                            JSONArray jArr = JSONPhotos.getJSONArray("photo");
                            for (int i = 0; i < jArr.length(); i++) {
                                JSONObject temp = jArr.getJSONObject(i);
                                String urlTemp = temp.get("url_l").toString();
                                String urlTemp2 = temp.get("id").toString();
                                String urlTemp3 = temp.get("owner").toString();
                                // Log.d("-",urlTemp);
                                urlsGrid.add(urlTemp);
                                idsGrid.add(urlTemp2);
                                ownersGrid.add(urlTemp3);
                            }
                            gridview.setAdapter(new ImageAdapter(getApplicationContext(),category,urlsGrid,idsGrid,ownersGrid));
                            Log.d("URLS: ",urlsGrid.toString());
                        } catch (Throwable t) {
                            Log.e("My App", "Could not parse malformed JSON:" + response);
                            Toast.makeText(getApplicationContext(), " Error retrieving data " , Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error: ", "Bad");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(ListPhoto.this, "" + position,
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ListPhoto.this,PhotoDetail.class);
                intent.putStringArrayListExtra("ids",idsGrid);
                intent.putStringArrayListExtra("urls",urlsGrid);
                intent.putExtra("position",position);
                //intent.putExtra("position",Integer.toString(position));
                startActivity(intent);
                //
                //intent.putExtra("Category", itemValue.getCategoryItem());
                //intent.putExtra("Photo", itemValue.getPhotoReference());
            }
        });
    }
}


