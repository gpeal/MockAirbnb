package com.airbnb.ui;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.R;
import com.airbnb.net.Listing;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ListingItemLayout extends RelativeLayout {

    @InjectView(R.id.listing_image) public ImageView mListingImageView;
    @InjectView(R.id.host_image) public ImageView mHostImageView;
    @InjectView(R.id.title) public TextView mTitleView;
    @InjectView(R.id.description) public TextView mDescriptionView;

    public ListingItemLayout(Context context) {
        this(context, null);
    }

    public ListingItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListingItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.listing_item_contents, this, true);
        ButterKnife.inject(this, this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Picasso.with(getContext()).cancelRequest(mListingImageView);
        Picasso.with(getContext()).cancelRequest(mHostImageView);
    }

    public void setListing(Listing listing) {
        if (listing.imageUri != null) {
            Drawable placeholder = new ColorDrawable(getResources().getColor(R.color.material_blue_grey_900));
            Picasso.with(getContext())
                    .load(listing.imageUri)
                    .placeholder(placeholder)
                    .fit()
                    .centerCrop()
                    .into(mListingImageView);
        }

        if (listing.hostImageUri != null) {
            Drawable placeholder = new ColorDrawable(getResources().getColor(R.color.material_blue_grey_800));
            Picasso.with(getContext())
                    .load(listing.hostImageUri)
                    .placeholder(placeholder)
                    .fit()
                    .centerCrop()
                    .into(mHostImageView);
        }

        // The dollar sign is slightly smaller and top aligned in the TextView.
        Spannable span = new SpannableString(listing.title);
        float relativeTextSize = 0.75f;
        span.setSpan(new RelativeSizeSpan(relativeTextSize), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new TopAlignmentSpan(relativeTextSize), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTitleView.setText(span);
        mDescriptionView.setText(listing.description);
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
