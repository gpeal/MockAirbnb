package com.airbnb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.net.HeroItem;
import com.squareup.picasso.Picasso;

public class HeroItemPresenter implements ListPresenter<HeroItem, HeroItemPresenter.ViewHolder> {

    @Override
    public ViewHolder createViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.hero_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, HeroItem heroItem) {
        Context context = holder.itemView.getContext();
        holder.titleView.setText(heroItem.title);
        holder.textView.setText(heroItem.text);
        if (heroItem.textBackgroundColor == 0) {
            holder.textView.setBackgroundColor(0);
        } else {
            holder.textView.setBackgroundColor(heroItem.textBackgroundColor);
        }
        if (heroItem.imageUri != null) {
            ImageView imageView = holder.backgroundImageView;
            Picasso.with(context)
                    .load(heroItem.imageUri)
                    .fit()
                    .centerCrop()
                    .into(imageView);
        }
    }

    @Override
    public void unbindViewHolder(ViewHolder holder) {
        Context context = holder.itemView.getContext();
        Picasso.with(context).cancelRequest(holder.backgroundImageView);
    }


    public static final class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleView;
        public TextView textView;
        public ImageView backgroundImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.title);
            textView = (TextView) itemView.findViewById(R.id.text);
            backgroundImageView = (ImageView) itemView.findViewById(R.id.background_image);
        }
    }
}
