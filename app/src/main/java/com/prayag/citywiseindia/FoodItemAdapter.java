package com.prayag.citywiseindia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder> {

    private List<FoodItem> foodItems;
    private OnFoodItemClickListener listener;

    public interface OnFoodItemClickListener {
        void onFoodItemClick(FoodItem foodItem);
    }

    public FoodItemAdapter(Context context, List<FoodItem> foodItems, OnFoodItemClickListener listener) {
        this.foodItems = foodItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food_card, parent, false);
        return new FoodItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemViewHolder holder, int position) {
        FoodItem foodItem = foodItems.get(position);
        holder.bind(foodItem, listener);
    }

    @Override
    public int getItemCount() {
        return foodItems == null ? 0 : foodItems.size();
    }

    static class FoodItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewFood;
        TextView textViewName;

        public FoodItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewFood = itemView.findViewById(R.id.imageView_food_item);
            textViewName = itemView.findViewById(R.id.textView_food_item_name);
        }

        public void bind(final FoodItem foodItem, final OnFoodItemClickListener listener) {
            textViewName.setText(foodItem.getName());

            if (foodItem.getImageResId() != 0) {
                Glide.with(itemView.getContext())
                        .load(foodItem.getImageResId())
                        .into(imageViewFood);
            }

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onFoodItemClick(foodItem);
                }
            });
        }
    }
}