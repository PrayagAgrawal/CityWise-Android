package com.prayag.citywiseindia;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import java.util.ArrayList;
import java.util.List;

public class SelectCityActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private citytileadapter interestsAdapter;
    private List<citytile> interestsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectcity);

        recyclerView = findViewById(R.id.interestsRecyclerView);

        loadInterests();

        interestsAdapter = new citytileadapter(interestsList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(interestsAdapter);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }

    private void loadInterests() {
        interestsList = new ArrayList<>();
        interestsList.add(new citytile("Indore", "A fast-growing city known for its cleanliness, food culture, Rajwada Palace, and blend of tradition with modern development.", R.drawable.citytile_indore));
        interestsList.add(new citytile("Mumbai", "The heart of Bollywood and business, Mumbai blends skyscrapers, beaches, and street life into an unstoppable city that never sleeps.", R.drawable.citytile_mumbai));
        interestsList.add(new citytile("Delhi", "India’s vibrant capital fuses ancient monuments, bustling markets, historic landmarks, and modern metro life into a culturally rich urban experience.", R.drawable.citytile_delhi));
        interestsList.add(new citytile("Bangalore", "Known as India’s Silicon Valley, Bangalore is a green, tech-driven city with modern offices, heritage sites, cafes, and a youthful energy.", R.drawable.citytile_bangalore));
        interestsList.add(new citytile("Jaipur", "The Pink City showcases royal palaces, colorful bazaars, forts, and desert charm—blending heritage, hospitality, and timeless architecture.", R.drawable.citytile_jaipur));
        interestsList.add(new citytile("Ahmedabad", "A Heritage city offering vibrant textile markets, bustling streets, and the scenic Sabarmati Riverfront—a perfect mix of heritage and urban growth.", R.drawable.citytile_ahmedabad));
        interestsList.add(new citytile("Pune", "A youthful and vibrant city that combines history, education, and IT industries. Home to forts, cafes, student life, and scenic hills.", R.drawable.citytile_pune));
    }
}