package com.prayag.citywiseindia;

public class CityAdventures {
    private final int imageResourceId;
    private final String title;
    private final String description;

    public CityAdventures(int imageResourceId, String title, String description) {
        this.imageResourceId = imageResourceId;
        this.title = title;
        this.description = description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}