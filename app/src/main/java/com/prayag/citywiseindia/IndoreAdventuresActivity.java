package com.prayag.citywiseindia;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.appbar.MaterialToolbar;
import java.util.ArrayList;
import java.util.List;

public class IndoreAdventuresActivity extends AppCompatActivity implements CityAdventuresAdapter.OnAdventureStartClickListener {

    private ViewPager2 indoreAdventuresViewPager;
    private CityAdventuresAdapter adapter;
    private List<CityAdventures> cityAdventuresList;
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indore_adventures);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        indoreAdventuresViewPager = findViewById(R.id.indoreAdventuresViewPager);

        loadAdventureData();

        adapter = new CityAdventuresAdapter(this, cityAdventuresList, this);
        indoreAdventuresViewPager.setAdapter(adapter);

        indoreAdventuresViewPager.setPageTransformer(new NoAnimationPageTransformer());
    }

    private void loadAdventureData() {
        cityAdventuresList = new ArrayList<>();
        cityAdventuresList.add(new CityAdventures(R.drawable.ralamandal2, "Ralamandal Wildlife Sanctuary", "A peaceful forested hill area for trekking, nature walks, and wildlife spotting."));
        cityAdventuresList.add(new CityAdventures(R.drawable.imagica, "Aqua Imagica Water Park", "A vibrant water paradise with thrilling slides, wave pools, and a perfect splash of fun for all."));
        cityAdventuresList.add(new CityAdventures(R.drawable.simcha, "Simcha Island - Adventure Park", "Adventure rides, nature zones, and leisure experiences in a scenic resort setting."));
        cityAdventuresList.add(new CityAdventures(R.drawable.mandu, "Short Trip to Mandu", "A hilltop town for a short getaway, offering scenic views and ancient architecture."));
        cityAdventuresList.add(new CityAdventures(R.drawable.heritage, "Heritage Train Ride", "A train journey offering breathtaking views between Patalpani and Kalakund."));
    }

    @Override
    public void onStartClicked(CityAdventures adventure) {
    }

    public static class ZoomOutPageTransformer implements ViewPager2.PageTransformer {
        @Override
        public void transformPage(@NonNull View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) {
                view.setAlpha(0f);
            } else if (position <= 1) {
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
                view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            } else {
                view.setAlpha(0f);
            }
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

    public static class NoAnimationPageTransformer implements ViewPager2.PageTransformer {
        @Override
        public void transformPage(@NonNull View page, float position) {
        }
    }
}