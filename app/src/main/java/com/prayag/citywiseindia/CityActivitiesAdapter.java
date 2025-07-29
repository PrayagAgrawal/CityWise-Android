package com.prayag.citywiseindia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CityActivitiesAdapter extends ListAdapter<CityActivities, CityActivitiesAdapter.ActivityViewHolder> {

    public CityActivitiesAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<CityActivities> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<CityActivities>() {
                @Override
                public boolean areItemsTheSame(@NonNull CityActivities oldItem, @NonNull CityActivities newItem) {
                    // Assuming title is unique enough for this example, or add an ID field
                    return oldItem.getTitle().equals(newItem.getTitle());
                }

                @Override
                public boolean areContentsTheSame(@NonNull CityActivities oldItem, @NonNull CityActivities newItem) {
                    return oldItem.getTitle().equals(newItem.getTitle()) &&
                            oldItem.getDescription().equals(newItem.getDescription()) &&
                            oldItem.getIconResource() == newItem.getIconResource() &&
                            oldItem.getCategories().equals(newItem.getCategories());
                }
            };

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.incity_activities_tile, parent, false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        CityActivities currentItem = getItem(position);
        holder.bind(currentItem);
    }

    static class ActivityViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImageView;
        TextView titleTextView;
        TextView descriptionTextView;

        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.imageView_activity_icon);
            titleTextView = itemView.findViewById(R.id.textView_activity_title);
            descriptionTextView = itemView.findViewById(R.id.textView_activity_description);
        }

        public void bind(CityActivities item) {
            titleTextView.setText(item.getTitle());
            descriptionTextView.setText(item.getDescription());
            iconImageView.setImageResource(item.getIconResource());
        }
    }
}