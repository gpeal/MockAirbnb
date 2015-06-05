package com.airbnb;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.airbnb.net.HeroItem;

import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<HeroItemPresenter.ViewHolder> {

    private final HeroItemPresenter mPresenter = new HeroItemPresenter();

    private List<HeroItem> mItems;

    @Override
    public HeroItemPresenter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mPresenter.createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(HeroItemPresenter.ViewHolder holder, int position) {
        mPresenter.bindViewHolder(holder, mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    public void setItems(List<HeroItem> items) {
        mItems = items;
        notifyDataSetChanged();
    }
}
