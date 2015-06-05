package com.airbnb;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.airbnb.net.ListItem;

import java.util.List;

public class SearchTabAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_HERO = 0;
    private static final int VIEW_TYPE_LISTING = 1;

    private final SparseArray<ListPresenter> mPresenters = new SparseArray<>(2);

    private List<ListItem> mItems;

    public SearchTabAdapter() {
        mPresenters.put(VIEW_TYPE_HERO, new HeroItemPresenter());
        mPresenters.put(VIEW_TYPE_LISTING, new ListingItemPresenter());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getPresenter(viewType).createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListItem item = mItems.get(position);
        int viewType = holder.getItemViewType();
        ListPresenter presenter = getPresenter(viewType);

        if (presenter instanceof HeroItemPresenter) {
            ((HeroItemPresenter) presenter).bindViewHolder((HeroItemPresenter.ViewHolder) holder, item.heroItem);
        } else if (presenter instanceof ListingItemPresenter) {
            ((ListingItemPresenter) presenter).bindViewHolder((ListingItemPresenter.ViewHolder) holder, item.listing);
        } else {
            throw new IllegalArgumentException("Don't know how to handle presenter of type " + presenter.getClass().getSimpleName());
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        getPresenter(holder.getItemViewType()).unbindViewHolder(holder);
    }

    @Override
    public int getItemViewType(int position) {
        ListItem item = mItems.get(position);
        if (item.heroItem != null) {
            return VIEW_TYPE_HERO;
        } else if (item.listing != null) {
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

    /**
     * Return the {@link ListPresenter} for the given view type or throw {@link IllegalArgumentException}.
     */
    @NonNull
    private ListPresenter getPresenter(int viewType) {
        ListPresenter presenter = mPresenters.get(viewType);
        if (presenter == null) {
            throw new IllegalArgumentException("No presenter for type " + viewType);
        }
        return presenter;
    }
}
