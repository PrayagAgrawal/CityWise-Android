package com.prayag.citywiseindia;

public class CityEvents {
    private int eventImageResId;
    private String title;
    private String description;
    private String cardLocation;
    private String cardDate;
    private String category;

    public CityEvents(int eventImageResId, String title, String description, String cardLocation, String cardDate, String category) {
        this.eventImageResId = eventImageResId;
        this.title = title;
        this.description = description;
        this.cardLocation = cardLocation;
        this.cardDate = cardDate;
        this.category = category;
    }

    public int getEventImageResId() {
        return eventImageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCardLocation() {
        return cardLocation;
    }

    public String getCardDate() {
        return cardDate;
    }

    public String getCategory() {
        return category;
    }
}