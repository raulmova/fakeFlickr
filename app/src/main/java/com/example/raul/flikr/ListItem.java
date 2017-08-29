package com.example.raul.flikr;

import android.graphics.drawable.Drawable;

import java.io.InputStream;

/**
 * Created by Raul on 25/08/2017.
 */

public class ListItem {
    private String photoReference;
    private String categoryItem;

    public ListItem(String categoryItem, String photoReference) {
        this.photoReference = photoReference;
        this.categoryItem = categoryItem;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public String getCategoryItem() {
        return categoryItem;
    }

    public void setCategoryItem(String categoryItem) {
        this.categoryItem = categoryItem;
    }


}
