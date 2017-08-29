package com.example.raul.flikr;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class PhotoDetail extends AppCompatActivity {

    ImageView ivDetail;
    TextView tvTitulo,tvDescripcion,tvAutor;
    ArrayList<String> idsGrid = new ArrayList<String>();
    ArrayList<String> urlsGrid = new ArrayList<String>();

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

        idsGrid = getIntent().getStringArrayListExtra("ids");
        urlsGrid = getIntent().getStringArrayListExtra("urls");
        position = getIntent().getIntExtra("position",0);
        ivDetail = (ImageView) findViewById(R.id.imageView);
        tvTitulo = (TextView) findViewById(R.id.textView2);
        tvDescripcion = (TextView) findViewById(R.id.textView3);
        tvAutor = (TextView) findViewById(R.id.textView4);
        Log.d("Integer: ",""+position);

        String url = "https://api.flickr.com/services/rest/?method=flickr.photos.getInfo&api_key=9b14be59a7d7007b1fae0ea005dc51af&format=json&photo_id=" + idsGrid.get(position) ;
        Log.d("String ID: ",url);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(String response) {
                        try {
                            response = response.substring(14, response.length() - 1);
                            JSONObject jObj = new JSONObject(response);
                            JSONObject JSONPhoto = jObj.getJSONObject("photo");
                            JSONObject temp1 = JSONPhoto.getJSONObject("owner");
                            JSONObject temp2 = JSONPhoto.getJSONObject("title");
                            JSONObject temp3 = JSONPhoto.getJSONObject("description");

                            tvAutor.setText("Autor : "+temp1.getString("realname"));
                            tvTitulo.setText("Título: "+temp2.getString("_content"));
                            tvDescripcion.setText(Html.fromHtml(temp3.getString("_content"),Html.FROM_HTML_MODE_COMPACT));
                            tvDescripcion.setMovementMethod(new ScrollingMovementMethod());

                            /*
                            JSONObject temp4 = JSONPhoto.getJSONObject("urls");
                            JSONArray jAr = temp4.getJSONArray("url");
                            JSONObject temp5 = jAr.getJSONObject(0);
                            */
                            Log.d("URL photo detail",urlsGrid.get(position));

                            //Picasso.with(getApplicationContext()).load().resize(300,300).into(ivDetail);
                            Picasso.with(getApplicationContext()).load(urlsGrid.get(position)).skipMemoryCache().fit().into(ivDetail);
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

    }
}
