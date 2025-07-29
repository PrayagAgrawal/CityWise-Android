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

public class CityEventsAdapter extends RecyclerView.Adapter<CityEventsAdapter.EventViewHolder> {

    private Context context;
    private List<CityEvents> eventList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(CityEvents event);
    }

    public CityEventsAdapter(Context context, List<CityEvents> eventList, OnItemClickListener listener) {
        this.context = context;
        this.eventList = eventList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.incity_events_tile, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        CityEvents event = eventList.get(position);
        holder.bind(event, listener);
    }

    @Override
    public int getItemCount() {
        return eventList == null ? 0 : eventList.size();
    }

    public void updateList(List<CityEvents> newList) {
        this.eventList = newList;
        notifyDataSetChanged();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewEvent;
        TextView textViewEventTitle;
        TextView textViewEventDescription;
        TextView textViewCardLocation;
        TextView textViewCardDate;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewEvent = itemView.findViewById(R.id.imageViewEvent);
            textViewEventTitle = itemView.findViewById(R.id.textViewEventTitle);
            textViewEventDescription = itemView.findViewById(R.id.textViewEventDescription);
            textViewCardLocation = itemView.findViewById(R.id.textViewCardLocation);
            textViewCardDate = itemView.findViewById(R.id.textViewCardDate);
        }

        public void bind(final CityEvents event, final OnItemClickListener listener) {
            textViewEventTitle.setText(event.getTitle());
            textViewEventDescription.setText(event.getDescription());
            textViewCardLocation.setText(event.getCardLocation());
            textViewCardDate.setText(event.getCardDate());

            Glide.with(itemView.getContext())
                    .load(event.getEventImageResId())
                    .into(imageViewEvent);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(event);
                }
            });
        }
    }
}