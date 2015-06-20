package com.airbnb;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.net.HeroItem;
import com.airbnb.ui.HeroItemLayout;

import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<HeroItem> mItems;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.hero_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((HeroItemLayout) holder.itemView).setHeroItem(mItems.get(position));
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
