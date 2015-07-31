package com.airbnb.ui

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

import com.airbnb.R
import com.airbnb.net.ListingItem
import com.squareup.picasso.Picasso

public class ListingItemLayout @jvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr) {

    private var mListingImageView: ImageView
    private var mHostImageView: ImageView
    private var mTitleView: TextView
    private var mDescriptionView: TextView

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.listing_item_contents, this, true)
        mListingImageView = findViewById(R.id.listing_image) as ImageView
        mHostImageView = findViewById(R.id.host_image) as ImageView
        mTitleView = findViewById(R.id.title) as TextView
        mDescriptionView = findViewById(R.id.description) as TextView
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Picasso.with(getContext()).cancelRequest(mListingImageView)
        Picasso.with(getContext()).cancelRequest(mHostImageView)
    }

    public fun setListing(listingItem: ListingItem) {
        if (listingItem.imageUri != null) {
            val placeholder = ColorDrawable(getResources().getColor(R.color.material_blue_grey_900))
            Picasso.with(getContext()).load(listingItem.imageUri).placeholder(placeholder).fit().centerCrop().into(mListingImageView)
        }

        if (listingItem.hostImageUri != null) {
            val placeholder = ColorDrawable(getResources().getColor(R.color.material_blue_grey_800))
            Picasso.with(getContext()).load(listingItem.hostImageUri).placeholder(placeholder).fit().centerCrop().into(mHostImageView)
        }
        mTitleView.setText(TextUtils.formatPrice(listingItem.title))
        mDescriptionView.setText(listingItem.description)
    }
}
