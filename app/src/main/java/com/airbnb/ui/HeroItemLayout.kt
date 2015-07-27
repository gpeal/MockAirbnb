package com.airbnb.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import butterknife.InjectView

import com.airbnb.R
import com.airbnb.net.HeroItem
import com.squareup.picasso.Picasso

public class HeroItemLayout @jvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    public var mTitleView: TextView
    public var mTextView: TextView
    public var mBackgroundImageView: ImageView

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.hero_item_contents, this, true)
        mTitleView = findViewById(R.id.title) as TextView
        mTextView = findViewById(R.id.text) as TextView
        mBackgroundImageView = findViewById(R.id.background_image) as ImageView
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Picasso.with(getContext()).cancelRequest(mBackgroundImageView)
    }

    public fun setHeroItem(heroItem: HeroItem) {
        mTitleView.setText(heroItem.title)
        mTextView.setText(heroItem.text)
        mTextView.setBackgroundColor(heroItem.textBackgroundColor)

        if (heroItem.imageUri != null) {
            Picasso.with(getContext())
                    .load(heroItem.imageUri)
                    .placeholder(R.color.background_light_grey)
                    .fit()
                    .centerCrop()
                    .into(mBackgroundImageView)
        } else {
            mBackgroundImageView.setBackgroundResource(R.color.background_light_grey)
        }
    }
}
