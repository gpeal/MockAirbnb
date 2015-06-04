package com.airbnb;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.net.HeroItem;
import com.airbnb.net.ListItem;
import com.airbnb.net.Listing;
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
        Context context = holder.itemView.getContext();
        HeroItem heroItem = mItems.get(position).heroItem;
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

    private void onBindListingViewHolder(ListingItemViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Listing listing = mItems.get(position).listing;
        if (listing.imageUri != null) {
            Drawable placeholder = new ColorDrawable(context.getResources().getColor(R.color.material_blue_grey_900));
            Picasso.with(context)
                    .load(listing.imageUri)
                    .placeholder(placeholder)
                    .fit()
                    .centerCrop()
                    .into(holder.listingImageView);
        }

        if (listing.hostImageUri != null) {
            Picasso.with(context)
                    .load(listing.hostImageUri)
                    .fit()
                    .centerCrop()
                    .into(holder.hostImageView);
        }

        // The dollar sign is slightly smaller and top aligned in the TextView.
        Spannable span = new SpannableString(listing.title);
        float relativeTextSize = 0.75f;
        span.setSpan(new RelativeSizeSpan(relativeTextSize), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new TopAlignmentSpan(relativeTextSize), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.titleView.setText(span);
        holder.textView.setText(listing.text);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        Context context = holder.itemView.getContext();
        if (holder.getItemViewType() == VIEW_TYPE_HERO) {
            HeroItemViewHolder hivh = (HeroItemViewHolder) holder;
            Picasso.with(context).cancelRequest(hivh.backgroundImageView);
        } else if (holder.getItemViewType() == VIEW_TYPE_LISTING) {
            ListingItemViewHolder lvh = (ListingItemViewHolder) holder;
            Picasso.with(context).cancelRequest(lvh.listingImageView);
            Picasso.with(context).cancelRequest(lvh.hostImageView);
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
        public ImageView listingImageView;
        public ImageView hostImageView;
        public TextView titleView;
        public TextView textView;

        public ListingItemViewHolder(View itemView) {
            super(itemView);
            listingImageView = (ImageView) itemView.findViewById(R.id.listing_image);
            hostImageView = (ImageView) itemView.findViewById(R.id.host_image);
            titleView = (TextView) itemView.findViewById(R.id.title);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }

    /**
     * Text span to align the smaller $ in a listing's title view to the top of the text.
     */
    private static final class TopAlignmentSpan extends MetricAffectingSpan {
        private final double mRatio;

        /**
         * @param relativeSize The relative size of the dollar sign compared to normal text.
         */
        public TopAlignmentSpan(double relativeSize) {
            mRatio = 1 - relativeSize;
        }

        @Override
        public void updateDrawState(TextPaint paint) {
            paint.baselineShift += (int) (paint.ascent() * mRatio);
        }

        @Override
        public void updateMeasureState(TextPaint paint) {
            paint.baselineShift += (int) (paint.ascent() * mRatio);
        }
    }
}
