package com.prayag.citywiseindia;

import androidx.annotation.DrawableRes;

public class FoodItem {
    private String id;
    private String name;
    private String description;

    @DrawableRes
    private int imageResId;

    public FoodItem(String id, String name, String description, @DrawableRes int imageResId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageResId = imageResId;
    }

    public FoodItem(String id, String name, @DrawableRes int imageResId) {
        this(id, name, null, imageResId);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }

    @DrawableRes
    public int getImageResId() { return imageResId; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
}