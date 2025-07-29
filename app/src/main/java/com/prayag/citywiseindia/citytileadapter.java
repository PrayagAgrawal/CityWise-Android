package com.prayag.citywiseindia;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class citytileadapter extends RecyclerView.Adapter<citytileadapter.InterestViewHolder> {

    private List<citytile> citytile;

    public citytileadapter(List<citytile> citytile) {
        this.citytile = citytile;
    }

    public static class InterestViewHolder extends RecyclerView.ViewHolder {
        ImageView interestImageView;
        TextView interestTitleTextView;
        TextView interestDescTextView;
        Button addInterestButton;

        public InterestViewHolder(@NonNull View itemView) {
            super(itemView);
            interestImageView = itemView.findViewById(R.id.interestImageView);
            interestTitleTextView = itemView.findViewById(R.id.interestTitleTextView);
            interestDescTextView = itemView.findViewById(R.id.interestDescTextView);
            addInterestButton = itemView.findViewById(R.id.addInterestButton);
        }
    }

    @NonNull
    @Override
    public InterestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.citytile, parent, false);
        return new InterestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InterestViewHolder holder, int position) {
        citytile currentInterest = citytile.get(position);

        Glide.with(holder.itemView.getContext())
                .load(currentInterest.getImageResId())
                .into(holder.interestImageView);
        holder.interestTitleTextView.setText(currentInterest.getTitle());
        holder.interestDescTextView.setText(currentInterest.getDescription());

        if (position == 0) {
            holder.addInterestButton.setOnClickListener(v -> {
                Intent intent = new Intent(holder.itemView.getContext(), IndoreCityActivity.class);
                holder.itemView.getContext().startActivity(intent);
            });
        }
        if (position == 1) {
            holder.addInterestButton.setOnClickListener(v -> {
                Intent intent = new Intent(holder.itemView.getContext(), MumbaiCityActivity.class);
                holder.itemView.getContext().startActivity(intent);
            });
        }
        if (position == 2) {
            holder.addInterestButton.setOnClickListener(v -> {
                Intent intent = new Intent(holder.itemView.getContext(), DelhiCityActivity.class);
                holder.itemView.getContext().startActivity(intent);
            });
        }
        if (position == 3) {
            holder.addInterestButton.setOnClickListener(v -> {
                Intent intent = new Intent(holder.itemView.getContext(), BangaloreCityActivity.class);
                holder.itemView.getContext().startActivity(intent);
            });
        }
        if (position == 4) {
            holder.addInterestButton.setOnClickListener(v -> {
                Intent intent = new Intent(holder.itemView.getContext(), JaipurCityActivity.class);
                holder.itemView.getContext().startActivity(intent);
            });
        }
        if (position == 5) {
            holder.addInterestButton.setOnClickListener(v -> {
                Intent intent = new Intent(holder.itemView.getContext(), AhmedabadCityActivity.class);
                holder.itemView.getContext().startActivity(intent);
            });
        }
        if (position == 6) {
            holder.addInterestButton.setOnClickListener(v -> {
                Intent intent = new Intent(holder.itemView.getContext(), PuneCityActivity.class);
                holder.itemView.getContext().startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return citytile.size();
    }
}