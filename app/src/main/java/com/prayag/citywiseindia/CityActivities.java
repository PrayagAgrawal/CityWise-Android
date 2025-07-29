package com.prayag.citywiseindia;
import androidx.annotation.DrawableRes;
import java.util.List;

public class CityActivities {
    @DrawableRes
    private int iconResource;
    private String title;
    private String description;
    private List<String> categories;

    public CityActivities(@DrawableRes int iconResource, String title, String description, List<String> categories) {
        this.iconResource = iconResource;
        this.title = title;
        this.description = description;
        this.categories = categories;
    }

    @DrawableRes
    public int getIconResource() {
        return iconResource;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getCategories() {
        return categories;
    }
}