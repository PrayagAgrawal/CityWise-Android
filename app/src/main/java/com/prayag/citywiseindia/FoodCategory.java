package com.prayag.citywiseindia;

import java.util.List;

public class FoodCategory {
    private String categoryId;
    private String title;
    private List<FoodItem> items;

    public FoodCategory(String categoryId, String title, List<FoodItem> items) {
        this.categoryId = categoryId;
        this.title = title;
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public List<FoodItem> getItems() {
        return items;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}