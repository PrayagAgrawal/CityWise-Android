package com.prayag.citywiseindia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import java.util.List;

public class CityAdventuresAdapter extends RecyclerView.Adapter<CityAdventuresAdapter.AdventureViewHolder> {

    private final Context context;
    private final List<CityAdventures> adventuresList;
    private final OnAdventureStartClickListener startClickListener;

    public interface OnAdventureStartClickListener {
        void onStartClicked(CityAdventures adventure);
    }

    public CityAdventuresAdapter(Context context, List<CityAdventures> adventuresList, OnAdventureStartClickListener listener) {
        this.context = context;
        this.adventuresList = adventuresList;
        this.startClickListener = listener;
    }

    @NonNull
    @Override
    public AdventureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.incity_adventure_tile, parent, false);
        return new AdventureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdventureViewHolder holder, int position) {
        CityAdventures adventure = adventuresList.get(position);

        holder.backgroundImage.setImageResource(adventure.getImageResourceId());
        holder.titleTextView.setText(adventure.getTitle());
        holder.descriptionTextView.setText(adventure.getDescription());

        holder.startButton.setOnClickListener(v -> {
            if (startClickListener != null) {
                startClickListener.onStartClicked(adventure);
            }
        });
    }

    @Override
    public int getItemCount() {
        return adventuresList.size();
    }

    static class AdventureViewHolder extends RecyclerView.ViewHolder {
        ImageView backgroundImage;
        TextView titleTextView;
        TextView descriptionTextView;
        TextView swipeTextView;
        MaterialButton startButton;

        public AdventureViewHolder(@NonNull View itemView) {
            super(itemView);
            backgroundImage = itemView.findViewById(R.id.adventureBackgroundImage);
            titleTextView = itemView.findViewById(R.id.adventureTitle);
            descriptionTextView = itemView.findViewById(R.id.adventureDescription);
            swipeTextView = itemView.findViewById(R.id.swipeText);
            startButton = itemView.findViewById(R.id.startButton);
        }
    }
}