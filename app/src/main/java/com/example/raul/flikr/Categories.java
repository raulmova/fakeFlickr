package com.example.raul.flikr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Categories extends AppCompatActivity {

    ListView lvCategories;
    String url;
    ArrayList<String> urls = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        lvCategories = (ListView)findViewById(R.id.lvCategories);

        ArrayList<ListItem> categories = new ArrayList<ListItem>();
        categories.add(new ListItem("Dogs","dog"));
        categories.add(new ListItem("Cinema","cinema"));
        categories.add(new ListItem("México","mexico"));
        categories.add(new ListItem("Books","notebook"));
        categories.add(new ListItem("Soccer","soccer"));
        categories.add(new ListItem("Tech","smartphone"));

        ArrayAdapter<ListItem> adapter = new CustomAdapter(this,categories);

        lvCategories.setAdapter(adapter);

        lvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ListView Clicked item index
                int itemPosition     = position;
                ListItem  itemValue    = (ListItem) lvCategories.getItemAtPosition(itemPosition);
              //  Toast.makeText(getApplicationContext(), " Code : " + itemValue.getCategoryItem(), Toast.LENGTH_SHORT).show();

                /////////////////////////
                Intent intent = new Intent(Categories.this,ListPhoto.class);
                intent.putExtra("Category", itemValue.getCategoryItem());
                intent.putExtra("Photo", itemValue.getPhotoReference());
               //intent.putStringArrayListExtra("Urls",urls);
               /* intent.putStringArrayListExtra("IDs",ids);
                intent.putStringArrayListExtra("Owners",owner);
                */
               startActivity(intent);
            }
        });

    }
}
