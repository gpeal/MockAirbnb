package com.airbnb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.net.HeroItem;
import com.airbnb.net.ListItem;
import com.squareup.picasso.Picasso;

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
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_HERO:
                onBindHeroViewHolder((HeroItemViewHolder) holder, position);
                break;
            case VIEW_TYPE_LISTING:
                onBindListingViewHolder((ListingItemViewHolder) holder, position);
                break;
        }
    }

    private void onBindHeroViewHolder(HeroItemViewHolder holder, int position) {
        ListItem item = mItems.get(position);
        HeroItem heroItem = item.heroItem;
        holder.titleView.setText(heroItem.title);
        holder.textView.setText(heroItem.text);
        if (heroItem.textBackgroundColor == 0) {
            holder.textView.setBackgroundColor(0);
        } else {
            holder.textView.setBackgroundColor(heroItem.textBackgroundColor);
        }
        if (heroItem.imageUri != null) {
            ImageView imageView = holder.backgroundImageView;
            Context context = holder.itemView.getContext();
            Picasso.with(context)
                    .load(heroItem.imageUri)
                    .fit()
                    .centerCrop()
                    .into(imageView);
        }
    }

    private void onBindListingViewHolder(ListingItemViewHolder holder, int position) {
        ListItem item = mItems.get(position);
        holder.titleView.setText(item.listing.title);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        if (holder.getItemViewType() == VIEW_TYPE_HERO) {
            HeroItemViewHolder hivh = (HeroItemViewHolder) holder;
            Context context = holder.itemView.getContext();
            Picasso.with(context).cancelRequest(hivh.backgroundImageView);
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
        public TextView textView;
        public ImageView backgroundImageView;

        public HeroItemViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.title);
            textView = (TextView) itemView.findViewById(R.id.text);
            backgroundImageView = (ImageView) itemView.findViewById(R.id.background_image);
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
