package com.airbnb;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.net.ListItem;

import java.util.List;

public class SearchTabAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_HERO = 0;
    private static final int VIEW_TYPE_LISTING = 1;

    private List<ListItem> mItems;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        switch(viewType) {
            case VIEW_TYPE_HERO:
                view = inflater.inflate(R.layout.hero_item, parent, false);
                return new HeroItemViewHolder(view);
            case VIEW_TYPE_LISTING:
                view = inflater.inflate(R.layout.listing_item, parent, false);
                return new ListingItemViewHolder(view);
            default:
                throw new IllegalStateException("Unsupported viewType (" + viewType + ")");

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListItem item = mItems.get(position);
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_HERO:
                ((HeroItemViewHolder) holder).titleView.setText(item.heroItem.title);
                break;
            case VIEW_TYPE_LISTING:
                ((ListingItemViewHolder) holder).titleView.setText(item.listing.title);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        ListItem item = mItems.get(position);
        if (item.heroItem != null) {
            return VIEW_TYPE_HERO;
        } else if (item.listing != null){
            return VIEW_TYPE_LISTING;
        } else {
            throw new IllegalArgumentException("Invalid list item at position (" + position + ")");
        }
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    public void setItems(List<ListItem> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public class HeroItemViewHolder extends RecyclerView.ViewHolder {

        public TextView titleView;

        public HeroItemViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.title);
        }
    }

    public class ListingItemViewHolder extends RecyclerView.ViewHolder {

        public TextView titleView;

        public ListingItemViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
