package com.prayag.citywiseindia;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class IndoreFoodActivity extends AppCompatActivity implements FoodCategoryAdapter.OnCategoryInteractionListener {

    private Toolbar toolbarFood;
    private TextView textViewFoodHeaderDescription;
    private RecyclerView recyclerViewFoodCategories;
    private FoodCategoryAdapter foodCategoryAdapter;
    private List<FoodCategory> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_indore_food);
        toolbarFood = findViewById(R.id.toolbar_food);
        recyclerViewFoodCategories = findViewById(R.id.recyclerView_food_categories);
        setupToolbar();
        setupRecyclerView();

        loadFoodData();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbarFood);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
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


    private void setupRecyclerView() {
        recyclerViewFoodCategories.setLayoutManager(new LinearLayoutManager(this));
        foodCategoryAdapter = new FoodCategoryAdapter(this, categoryList, this);
        recyclerViewFoodCategories.setAdapter(foodCategoryAdapter);
    }

    private void loadFoodData() {

        List<FoodItem> breakfastItems = new ArrayList<>();
        breakfastItems.add(new FoodItem("breakfast1", "Poha", R.drawable.poha));
        breakfastItems.add(new FoodItem("breakfast2", "Kachori", R.drawable.kachori));
        breakfastItems.add(new FoodItem("breakfast3", "Jalebi", R.drawable.jalebi));
        breakfastItems.add(new FoodItem("breakfast4", "Samosa", R.drawable.samosa));


        List<FoodItem> lunchDinnerItems = new ArrayList<>();
        lunchDinnerItems.add(new FoodItem("lunchDinner1", "Dal Bafle", R.drawable.dal_bafle));
        lunchDinnerItems.add(new FoodItem("lunchDinner2", "Paneer and Naan", R.drawable.paneer_naan));
        lunchDinnerItems.add(new FoodItem("lunchDinner3", "Chole Bhature", R.drawable.chole_bhature));
        lunchDinnerItems.add(new FoodItem("lunchDinner4", "Thali", R.drawable.thali));

        List<FoodItem> mustTryItems = new ArrayList<>();
        mustTryItems.add(new FoodItem("mustTry1", "Khopra Patties", R.drawable.khopra_patties));
        mustTryItems.add(new FoodItem("mustTry2", "Shikanji", R.drawable.shikanji));
        mustTryItems.add(new FoodItem("mustTry3", "Dahi Vada", R.drawable.dahi_vada));
        mustTryItems.add(new FoodItem("mustTry4", "Bhutte Ka Kees", R.drawable.bhutte_kees));

        List<FoodItem> streetFoodItems = new ArrayList<>();
        streetFoodItems.add(new FoodItem("streetFood1", "Hot Dog", R.drawable.hotdog));
        streetFoodItems.add(new FoodItem("streetFood2", "Dal Pakwan", R.drawable.dal_pakwan));
        streetFoodItems.add(new FoodItem("streetFood3", "Garadu", R.drawable.garadu));
        streetFoodItems.add(new FoodItem("streetFood4", "Baked Samosa", R.drawable.baked_samosa));

        List<FoodItem> southIndianItems = new ArrayList<>();
        southIndianItems.add(new FoodItem("southIndian1", "Dosa", R.drawable.dosa));
        southIndianItems.add(new FoodItem("southIndian2", "Idli Sambar", R.drawable.idli));
        southIndianItems.add(new FoodItem("southIndian3", "Medu Vada", R.drawable.medu_vada));
        southIndianItems.add(new FoodItem("southIndian4", "Uttapam", R.drawable.uttapam));

        List<FoodItem> fastFoodItems = new ArrayList<>();
        fastFoodItems.add(new FoodItem("fastFood1", "Pizza", R.drawable.pizza));
        fastFoodItems.add(new FoodItem("fastFood2", "Pasta", R.drawable.pasta));
        fastFoodItems.add(new FoodItem("fastFood3", "Burger", R.drawable.burger));
        fastFoodItems.add(new FoodItem("fastFood4", "Sandwich", R.drawable.sandwich));

        List<FoodItem> chineseItems = new ArrayList<>();
        chineseItems.add(new FoodItem("chinese1", "Noodles", R.drawable.noodles));
        chineseItems.add(new FoodItem("chinese2", "Manchurian", R.drawable.manchurian));
        chineseItems.add(new FoodItem("chinese3", "Fried Rice", R.drawable.fried_rice));
        chineseItems.add(new FoodItem("chinese4", "Chili Paneer", R.drawable.chilli_paneer));

        List<FoodItem> sweetToothItems = new ArrayList<>();
        sweetToothItems.add(new FoodItem("sweetTooth1", "Rasgulla", R.drawable.rasgulla));
        sweetToothItems.add(new FoodItem("sweetTooth2", "Khopra Pak", R.drawable.khopra_pak));
        sweetToothItems.add(new FoodItem("sweetTooth3", "Jalebi Rabdi", R.drawable.jalebi_rabdi));
        sweetToothItems.add(new FoodItem("sweetTooth4", "gajak", R.drawable.gajak));


        categoryList.clear();
        categoryList.add(new FoodCategory("breakfast", "Breakfast", breakfastItems));
        categoryList.add(new FoodCategory("lunch", "Lunch/Dinner", lunchDinnerItems));
        categoryList.add(new FoodCategory("must_try", "Must Try", mustTryItems));
        categoryList.add(new FoodCategory("street_food", "Street Food", streetFoodItems));
        categoryList.add(new FoodCategory("south_indian", "South Indian", southIndianItems));
        categoryList.add(new FoodCategory("fast_food", "Fast Food", fastFoodItems));
        categoryList.add(new FoodCategory("chinese", "Chinese", chineseItems));
        categoryList.add(new FoodCategory("sweet_tooth", "Sweet Tooth", sweetToothItems));


        foodCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSeeAllClicked(FoodCategory category) {
        // Intent intent = new Intent(this, AllItemsInCategoryActivity.class);
    }

    @Override
    public void onFoodItemClicked(FoodItem foodItem, FoodCategory category) {
        // Intent intent = new Intent(this, FoodItemDetailActivity.class);
    }
}