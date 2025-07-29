package com.prayag.citywiseindia;

import androidx.annotation.DrawableRes;

public class AttractionItem {
    private String title;
    @DrawableRes
    private int imageResId;

    public AttractionItem(String title, @DrawableRes int imageResId) {
        this.title = title;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DrawableRes
    public int getImageResId() {
        return imageResId;
    }
}