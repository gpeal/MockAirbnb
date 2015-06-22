package com.airbnb;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.net.ListItem;
import com.airbnb.ui.HeroItemLayout;
import com.airbnb.ui.ListingItemLayout;

import java.util.List;

public class SearchTabAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String TAG = "SearchTabAdapter";
    private static final int VIEW_TYPE_HERO = 0;
    private static final int VIEW_TYPE_LISTING = 1;

    private final MainActivity mActivity;

    private List<ListItem> mItems;

    public SearchTabAdapter(MainActivity activity) {
        mActivity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        @LayoutRes int layoutRes;
        switch (viewType) {
            case VIEW_TYPE_HERO:
                layoutRes = R.layout.hero_item;
                break;
            case VIEW_TYPE_LISTING:
                layoutRes = R.layout.listing_item;
                break;
            default:
                throw new IllegalArgumentException("No layout for viewType " + viewType);
        }
        try {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(layoutRes, parent, false);
            return new ViewHolder(view);
        } catch (Throwable t) {
            Log.e(TAG, "Error inflating view", t);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ListItem item = mItems.get(position);
        switch(holder.getItemViewType()) {
            case VIEW_TYPE_HERO:
                ((HeroItemLayout) holder.itemView).setHeroItem(item.heroItem);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mActivity.showHeroItem(item.heroItem.id);
                    }
                });
                break;
            case VIEW_TYPE_LISTING:
                ((ListingItemLayout) holder.itemView).setListing(item.listingItem);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mActivity.showListing(item.listingItem.id);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        ListItem item = mItems.get(position);
        if (item.heroItem != null) {
            return VIEW_TYPE_HERO;
        } else if (item.listingItem != null) {
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
}
