package com.prayag.citywiseindia;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class IndoreEventsActivity extends AppCompatActivity implements CityEventsAdapter.OnItemClickListener {

    private TextView textViewLocationHeader;
    private TextView textViewLocationLabel;
    private TextInputLayout searchBarLayout;
    private TextInputEditText searchEditText;
    private ChipGroup filterChipGroup;
    private RecyclerView recyclerViewEvents;
    private CityEventsAdapter cityEventsAdapter;
    private List<CityEvents> allEventsList;
    private List<CityEvents> currentlyDisplayedEvents;
    private String currentFilterCategory = "All";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indore_events);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textViewLocationHeader = findViewById(R.id.textViewLocation);
        textViewLocationLabel = findViewById(R.id.textView5);
        searchBarLayout = findViewById(R.id.searchBarLayout);
        searchEditText = findViewById(R.id.searchEditText);
        filterChipGroup = findViewById(R.id.filterChipGroup);
        recyclerViewEvents = findViewById(R.id.recyclerViewEvents);

        allEventsList = new ArrayList<>();
        currentlyDisplayedEvents = new ArrayList<>();

        setupRecyclerView();
        setupLocationDisplay();
        setupSearch();
        setupFilterChips();

        loadEventsData();
    }

    private void setupLocationDisplay() {
    }

    private void setupRecyclerView() {
        recyclerViewEvents.setLayoutManager(new LinearLayoutManager(this));
        cityEventsAdapter = new CityEventsAdapter(this, currentlyDisplayedEvents, this);
        recyclerViewEvents.setAdapter(cityEventsAdapter);
    }

    private void setupSearch() {
        searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String query = searchEditText.getText().toString().trim();
                performSearch(query);
                hideKeyboard();
                return true;
            }
            return false;
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        searchEditText.clearFocus();
    }


    private void setupFilterChips() {
        currentFilterCategory = "All";
        
        filterChipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.isEmpty()) {
                currentFilterCategory = "All";
            } else {
                Chip selectedChip = group.findViewById(checkedIds.get(0));
                if (selectedChip != null) {
                    currentFilterCategory = selectedChip.getText().toString();
                } else {
                    currentFilterCategory = "All";
                }
            }
            applyFiltersAndSearch();
        });
    }

    private void loadEventsData() {
        allEventsList.clear();
        allEventsList.add(new CityEvents(
                R.drawable.honeysingh,
                "Honey Singh Concert",
                "A nationwide concert series celebrating the success of the track Millionaire.",
                "C21 Estate", "Aug 07", "Music"
        ));
        allEventsList.add(new CityEvents(
                R.drawable.sunburn,
                "Sunburn Music Fest",
                "Experience the best annual music festival with top artists from around the world.",
                "Sunburn Campus", "Sep 15-17", "Music"
        ));
        allEventsList.add(new CityEvents(
                R.drawable.marathon,
                "City Marathon 2025",
                "Run the 10K city marathon starting and ending at Nehru Stadium.",
                "Nehru Stadium", "Oct 12", "Sport"
        ));
        allEventsList.add(new CityEvents(
                R.drawable.cricket,
                "CSK vs RCB",
                "Catch the Southern giants clash in Indore with fierce rivalries and big hits.",
                "Holkar Stadium", "Nov 10", "Sport"
        ));
        allEventsList.add(new CityEvents(
                R.drawable.aakash,
                "Aakash Gupta Stand-up",
                "Get ready to LOL; you're signing up for something totally worth it!",
                "Papaya Tree", "Oct 20", "Comedy"
        ));
        allEventsList.add(new CityEvents(
                R.drawable.ravi,
                "Ravi Gupta Stand-up",
                "Forget your Kal ki Chinta and Join us in this super funny Show by Ravi Gupta.",
                "Ravindra Natya Grah", "Nov 05", "Comedy"
        ));
        allEventsList.add(new CityEvents(
                R.drawable.swiggy,
                "Swiggy Food Festival",
                "Swiggy Food Festival travels to your city and you’re in for a delightful treat!",
                "Jalsa Resort", "Jul 22", "Food"
        ));
        allEventsList.add(new CityEvents(
                R.drawable.foodfest,
                "TGI Food Festival",
                "Experience the rich flavors and cultural diversity of India.",
                "Phoenix Citadel", "Sep 29", "Food"
        ));
        allEventsList.add(new CityEvents(
                R.drawable.hack,
                "HackNdore - Hackathon",
                "Central India's largest technology event, brought to you by Indore Municipal Corporation.",
                "AITR", "Jan 13", "Technology"
        ));
        allEventsList.add(new CityEvents(
                R.drawable.conclave,
                "MP Tech Growth Conclave",
                "State's long-term vision for digital transformation and innovation-led economic development.",
                "Brilliant Convention Centre", "Apr 27", "Technology"
        ));

        applyFiltersAndSearch();
    }

    private void performSearch(String query) {
        applyFiltersAndSearch();
    }

    private void applyFiltersAndSearch() {
        String searchQuery = searchEditText.getText().toString().trim().toLowerCase(Locale.getDefault());
        List<CityEvents> filteredAndSearchedList = new ArrayList<>();

        for (CityEvents event : allEventsList) {
            boolean categoryMatch = "All".equalsIgnoreCase(currentFilterCategory) ||
                    event.getCategory().equalsIgnoreCase(currentFilterCategory);

            if (categoryMatch) {
                if (searchQuery.isEmpty()) {
                    filteredAndSearchedList.add(event);
                } else {
                    if (event.getTitle().toLowerCase(Locale.getDefault()).contains(searchQuery) ||
                            event.getDescription().toLowerCase(Locale.getDefault()).contains(searchQuery) ||
                            event.getCardLocation().toLowerCase(Locale.getDefault()).contains(searchQuery)) {
                        filteredAndSearchedList.add(event);
                    }
                }
            }
        }
        currentlyDisplayedEvents.clear();
        currentlyDisplayedEvents.addAll(filteredAndSearchedList);
        cityEventsAdapter.updateList(currentlyDisplayedEvents);
    }


    @Override
    public void onItemClick(CityEvents event) {
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