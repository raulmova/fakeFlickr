package com.example.raul.flikr;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Raul on 25/08/2017.
 */

public class CustomAdapter extends ArrayAdapter {
    private TextView tvName;
    private ImageView ivItem;

    public CustomAdapter(Context context, ArrayList<ListItem> cities){
        super(context, R.layout.categories, cities);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        //Definir layout inflater
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        //Asociar Layout Inflater a la vista custom
        View vistaCustom = layoutInflater.inflate(R.layout.categories,parent,false);

        //Obtener el elemento de arrreglo en la position
        ListItem item = (ListItem)getItem(position);

        //Definir textView
        tvName = (TextView) vistaCustom.findViewById(R.id.tvCategory);
        ivItem = (ImageView) vistaCustom.findViewById(R.id.ivItem);

        tvName.setText(item.getCategoryItem());
        Context c = getContext();
        int resourceId = c.getResources().getIdentifier(item.getPhotoReference(),"drawable",c.getPackageName());
        ivItem.setImageResource(resourceId);

        //ivItem.setImageResource(Resources.getSystem().getIdentifier(item.getPhotoReference(),null,c.getPackageName()));
        return vistaCustom;
    }

}