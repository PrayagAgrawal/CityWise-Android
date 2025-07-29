package com.prayag.citywiseindia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FoodCategoryAdapter extends RecyclerView.Adapter<FoodCategoryAdapter.FoodCategoryViewHolder> {

    private List<FoodCategory> foodCategories;
    private Context context;
    private OnCategoryInteractionListener listener;

    public interface OnCategoryInteractionListener {
        void onSeeAllClicked(FoodCategory category);
        void onFoodItemClicked(FoodItem foodItem, FoodCategory category);
    }

    public FoodCategoryAdapter(Context context, List<FoodCategory> foodCategories, OnCategoryInteractionListener listener) {
        this.context = context;
        this.foodCategories = foodCategories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FoodCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food_category, parent, false);
        return new FoodCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodCategoryViewHolder holder, int position) {
        FoodCategory category = foodCategories.get(position);
        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return foodCategories == null ? 0 : foodCategories.size();
    }

    public void setData(List<FoodCategory> newCategories) {
        this.foodCategories = newCategories;
        notifyDataSetChanged();
    }


    class FoodCategoryViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCategoryTitle;
        TextView textViewSeeAll;
        RecyclerView innerRecyclerView;

        public FoodCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCategoryTitle = itemView.findViewById(R.id.textView_category_title);
            textViewSeeAll = itemView.findViewById(R.id.textView_see_all);
            innerRecyclerView = itemView.findViewById(R.id.recyclerView_inner_food_items);
        }

        void bind(final FoodCategory category) {
            textViewCategoryTitle.setText(category.getTitle());

            textViewSeeAll.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onSeeAllClicked(category);
                }
            });

            innerRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

            FoodItemAdapter.OnFoodItemClickListener innerItemClickListener = foodItem -> {
                if (listener != null) {
                    listener.onFoodItemClicked(foodItem, category);
                }
            };

            FoodItemAdapter foodItemAdapter = new FoodItemAdapter(context, category.getItems(), innerItemClickListener);
            innerRecyclerView.setAdapter(foodItemAdapter);
        }
    }
}