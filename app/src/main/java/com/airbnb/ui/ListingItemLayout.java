package com.airbnb.ui;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.R;
import com.airbnb.net.ListingItem;
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

    public void setListing(ListingItem listingItem) {
        if (listingItem.imageUri != null) {
            Drawable placeholder = new ColorDrawable(getResources().getColor(R.color.material_blue_grey_900));
            Picasso.with(getContext())
                    .load(listingItem.imageUri)
                    .placeholder(placeholder)
                    .fit()
                    .centerCrop()
                    .into(mListingImageView);
        }

        if (listingItem.hostImageUri != null) {
            Drawable placeholder = new ColorDrawable(getResources().getColor(R.color.material_blue_grey_800));
            Picasso.with(getContext())
                    .load(listingItem.hostImageUri)
                    .placeholder(placeholder)
                    .fit()
                    .centerCrop()
                    .into(mHostImageView);
        }
        mTitleView.setText(TextUtils.formatPrice(listingItem.title));
        mDescriptionView.setText(listingItem.description);
    }
}
