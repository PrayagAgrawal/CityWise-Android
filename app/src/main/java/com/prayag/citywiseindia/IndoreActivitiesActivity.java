package com.prayag.citywiseindia;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IndoreActivitiesActivity extends AppCompatActivity {

    private RecyclerView recyclerViewActivities;
    private CityActivitiesAdapter activityAdapter;
    private List<CityActivities> masterActivityList;
    private ChipGroup chipGroupFilters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indore_activities);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        recyclerViewActivities = findViewById(R.id.recyclerView_activities);
        chipGroupFilters = findViewById(R.id.chipGroup_filters);

        loadMasterActivityData();

        activityAdapter = new CityActivitiesAdapter();
        recyclerViewActivities.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewActivities.setAdapter(activityAdapter);

        chipGroupFilters.clearCheck();

        setupChipGroupListener();

        if (masterActivityList != null && !masterActivityList.isEmpty()) {
            activityAdapter.submitList(new ArrayList<>(masterActivityList));
        } else {
            activityAdapter.submitList(new ArrayList<>());
            Toast.makeText(this, "No activities loaded.", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadMasterActivityData() {
        masterActivityList = new ArrayList<>();
        masterActivityList.add(new CityActivities(R.drawable.arcade, "Arcade Games", "Play classic and modern arcade games",  Arrays.asList("Indoor", "Group", "Solo")));
        masterActivityList.add(new CityActivities(R.drawable.bowling, "Bowling", "Knock down pins with friends or family",  Arrays.asList("Indoor", "Group")));
        masterActivityList.add(new CityActivities(R.drawable.minigolf, "Mini Golf", "Putt your way through creative courses",  Arrays.asList("indoor", "Group", "Solo")));
        masterActivityList.add(new CityActivities(R.drawable.vrexp, "VR Experiences", "Play classic and modern arcade games",  Arrays.asList("Indoor", "Group", "Solo")));
        masterActivityList.add(new CityActivities(R.drawable.pool, "Pool", "Challenge yourself with a game of pool",  Arrays.asList("indoor", "Solo", "Group")));
        masterActivityList.add(new CityActivities(R.drawable.board, "Board Game Cafe", "Enjoy a variety of board games with snacks",  Arrays.asList("Indoor", "Group")));
        masterActivityList.add(new CityActivities(R.drawable.lounge, "Gaming Lounge", "Dive into console and PC gaming setups", Arrays.asList("Indoor", "Group", "Solo")));
        masterActivityList.add(new CityActivities(R.drawable.paintball, "Paintball Arena", "Team up and battle it out with paintball guns", Arrays.asList("Outdoor", "Group")));
        masterActivityList.add(new CityActivities(R.drawable.boating, "Boating", "Relax with a paddle boat ride on calm waters", Arrays.asList("Outdoor", "Solo", "Group")));
        masterActivityList.add(new CityActivities(R.drawable.party, "Club Party", "Dance the night away at Indore’s top clubs", Arrays.asList("indoor", "Group")));
        masterActivityList.add(new CityActivities(R.drawable.cycling, "Cycling Trail","Ride through scenic lakefront or city routes", Arrays.asList("Outdoor", "Solo", "Group")));
        masterActivityList.add(new CityActivities(R.drawable.streetfood, "Street Food", "Explore Sarafa and 56 Dukaan for iconic bites", Arrays.asList("Outdoor", "Solo", "Group")));
    }

    private void setupChipGroupListener() {
        chipGroupFilters.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.isEmpty()) {
                activityAdapter.submitList(new ArrayList<>(masterActivityList));
                return;
            }
            Chip checkedChip = group.findViewById(checkedIds.get(0));
            if (checkedChip != null) {
                String selectedCategory = checkedChip.getText().toString();
                filterActivitiesByChipText(selectedCategory);
            }
        });
    }

    private void filterActivitiesByChipText(String category) {
        List<CityActivities> filteredList;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            filteredList = masterActivityList.stream()
                    .filter(item -> item.getCategories().contains(category))
                    .collect(Collectors.toList());
        } else {
            filteredList = new ArrayList<>();
            for (CityActivities item : masterActivityList) {
                if (item.getCategories().contains(category)) {
                    filteredList.add(item);
                }
            }
        }
        activityAdapter.submitList(filteredList);

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No activities found for " + category, Toast.LENGTH_SHORT).show();
        }
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