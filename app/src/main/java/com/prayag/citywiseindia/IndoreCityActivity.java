package com.prayag.citywiseindia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

public class IndoreCityActivity extends AppCompatActivity implements AttractionAdapter.OnAttractionClickListener {

    private RecyclerView recyclerViewCityAttractions;
    private AttractionAdapter attractionAdapter;
    private ImageView imageViewCityHeader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.indore_city);

        imageViewCityHeader = findViewById(R.id.imageView);

        if (imageViewCityHeader != null) {
            Glide.with(this)
                    .load(R.drawable.citytile_indore)
                    .into(imageViewCityHeader);
        }

        recyclerViewCityAttractions = findViewById(R.id.recyclerViewCityAttractions);
        recyclerViewCityAttractions.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCityAttractions.setHasFixedSize(true);

        //initialize adapter
        attractionAdapter = new AttractionAdapter(this);
        recyclerViewCityAttractions.setAdapter(attractionAdapter);

        loadAttractionData();
    }

    private void loadAttractionData() {
        List<Attraction> sampleAttractions = new ArrayList<>();
        sampleAttractions.add(new Attraction("food", "Food", "Famous dishes and must-try local street food.", R.drawable.rec_food));
        sampleAttractions.add(new Attraction("attraction", "Attractions", "Iconic spots and landmarks you can’t miss.", R.drawable.rec_attractions));
        sampleAttractions.add(new Attraction("activities", "Activities", "Fun things to do around the city.", R.drawable.rec_activities));
        sampleAttractions.add(new Attraction("adventures", "Adventures", "Thrilling outdoor experiences and challenges.", R.drawable.rec_adventures));
        sampleAttractions.add(new Attraction("upcomingevents", "Upcoming Events", "Exciting festivals, fairs, and local happenings.", R.drawable.rec_events));

        attractionAdapter.submitList(sampleAttractions);
    }

    @Override
    public void onAttractionClick(Attraction attraction) {
        Intent intent = null;

        switch (attraction.getId()) {
            case "food":
                intent = new Intent(this, IndoreFoodActivity.class);
                break;
            case "attraction":
                intent = new Intent(this, IndoreAttractionsActivity.class);
                break;
            case "activities":
                intent = new Intent(this, IndoreActivitiesActivity.class);
                break;
            case "adventures":
                intent = new Intent(this, IndoreAdventuresActivity.class);
                break;
            case "upcomingevents":
                intent = new Intent(this, IndoreEventsActivity.class);
                break;
            default:
                return;
        }

        //if an intent is created, pass data and start the activity
        if (intent != null) {
            intent.putExtra("ATTRACTION_ID", attraction.getId());
            intent.putExtra("ATTRACTION_TITLE", attraction.getTitle());
            startActivity(intent);
        }
    }
}