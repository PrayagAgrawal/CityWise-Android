package com.prayag.citywiseindia; // Or your specific adapter package

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

public class AttractionAdapterIndore extends RecyclerView.Adapter<AttractionAdapterIndore.AttractionViewHolder> {

    private Context context;
    private List<AttractionItem> attractionList;

    public AttractionAdapterIndore(Context context, List<AttractionItem> attractionList) {
        this.context = context;
        this.attractionList = attractionList;
    }

    @NonNull
    @Override
    public AttractionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.incity_attraction_tile, parent, false);
        return new AttractionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttractionViewHolder holder, int position) {
        AttractionItem currentAttraction = attractionList.get(position);

        holder.textViewTitleOnImage.setText(currentAttraction.getTitle());

        Glide.with(context)
                .load(currentAttraction.getImageResId())
                .into(holder.imageViewBanner);
    }

    @Override
    public int getItemCount() {
        return attractionList == null ? 0 : attractionList.size();
    }

    static class AttractionViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewBanner;
        TextView textViewTitleOnImage;

        public AttractionViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewBanner = itemView.findViewById(R.id.imageView_attraction_banner);
            textViewTitleOnImage = itemView.findViewById(R.id.textView_attraction_title_on_image);
        }
    }
}