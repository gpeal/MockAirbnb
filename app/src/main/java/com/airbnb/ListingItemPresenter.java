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

import com.airbnb.net.Listing;
import com.squareup.picasso.Picasso;

public class ListingItemPresenter implements ListPresenter<Listing, ListingItemPresenter.ViewHolder> {

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listing_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, Listing listing) {
        Context context = holder.itemView.getContext();
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
    public void unbindViewHolder(ViewHolder holder) {
        Context context = holder.itemView.getContext();
        Picasso.with(context).cancelRequest(holder.listingImageView);
        Picasso.with(context).cancelRequest(holder.hostImageView);
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView listingImageView;
        public ImageView hostImageView;
        public TextView titleView;
        public TextView textView;

        public ViewHolder(View itemView) {
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
