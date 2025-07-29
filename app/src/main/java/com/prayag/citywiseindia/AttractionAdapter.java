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
import com.bumptech.glide.Glide;

public class AttractionAdapter extends ListAdapter<Attraction, AttractionAdapter.AttractionViewHolder> {

    public interface OnAttractionClickListener {
        void onAttractionClick(Attraction attraction);
    }

    private OnAttractionClickListener clickListener;

    public AttractionAdapter(OnAttractionClickListener listener) {
        super(DIFF_CALLBACK);
        this.clickListener = listener;
    }

    private static final DiffUtil.ItemCallback<Attraction> DIFF_CALLBACK = new DiffUtil.ItemCallback<Attraction>() {
        @Override
        public boolean areItemsTheSame(@NonNull Attraction oldItem, @NonNull Attraction newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Attraction oldItem, @NonNull Attraction newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getImageResId() == newItem.getImageResId();
        }
    };

    @NonNull
    @Override
    public AttractionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_attraction_card, parent, false);
        return new AttractionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AttractionViewHolder holder, int position) {
        Attraction currentAttraction = getItem(position);

        holder.textViewAttractionTitle.setText(currentAttraction.getTitle());
        holder.textViewAttractionDescription.setText(currentAttraction.getDescription());

        Glide.with(holder.itemView.getContext())
                .load(currentAttraction.getImageResId())
                .into(holder.imageViewAttractionPhoto);

        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onAttractionClick(currentAttraction);
            }
        });
    }

    static class AttractionViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewAttractionPhoto;
        TextView textViewAttractionTitle;
        TextView textViewAttractionDescription;

        public AttractionViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewAttractionPhoto = itemView.findViewById(R.id.imageViewAttractionPhoto);
            textViewAttractionTitle = itemView.findViewById(R.id.textViewAttractionTitle);
            textViewAttractionDescription = itemView.findViewById(R.id.textViewAttractionDescription);
        }
    }
}