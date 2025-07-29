package com.prayag.citywiseindia;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import java.util.ArrayList;
import java.util.List;

public class IndoreAttractionsActivity extends AppCompatActivity {

    private static final String TAG = "IndoreAttractions";
    private RecyclerView recyclerViewAttractions;
    private AttractionAdapterIndore attractionCardAdapter;
    private List<AttractionItem> attractionDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indore_attractions);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_attractions);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        recyclerViewAttractions = findViewById(R.id.imageView_attraction_banner);
        attractionDataList = new ArrayList<>();

        attractionCardAdapter = new AttractionAdapterIndore(this, attractionDataList);

        recyclerViewAttractions.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAttractions.setAdapter(attractionCardAdapter);

        loadSampleAttractionData();
    }

    private void loadSampleAttractionData() {
        attractionDataList.add(new AttractionItem("Rajwada Palace", R.drawable.rajwada));
        attractionDataList.add(new AttractionItem("Lal Bagh Palace", R.drawable.lalbhag));
        attractionDataList.add(new AttractionItem("Indore Zoo", R.drawable.indorezoo));
        attractionDataList.add(new AttractionItem("Central Museum", R.drawable.centralmuseum));
        attractionDataList.add(new AttractionItem("Lotus Valley", R.drawable.lotus));
        attractionDataList.add(new AttractionItem("Sirpur lake", R.drawable.sirpur));
        attractionDataList.add(new AttractionItem("Pitra Parvat", R.drawable.pitra));
        attractionDataList.add(new AttractionItem("Tincha Waterfall", R.drawable.tincha));
        attractionDataList.add(new AttractionItem("Phoenix Citadel", R.drawable.phoenix));
        attractionDataList.add(new AttractionItem("Ralamandal Wildlife Sanctuary", R.drawable.ralamandal
        ));

        if (attractionDataList.isEmpty()) {
            Log.w(TAG, "No attraction data loaded!");
        } else {
            Log.d(TAG, "Data loaded. Number of attractions: " + attractionDataList.size());
        }
        attractionCardAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}