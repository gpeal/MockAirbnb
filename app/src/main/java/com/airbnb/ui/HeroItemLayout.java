package com.airbnb.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.R;
import com.airbnb.net.HeroItem;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HeroItemLayout extends FrameLayout {

    @InjectView(R.id.title) public TextView mTitleView;
    @InjectView(R.id.text) public TextView mTextView;
    @InjectView(R.id.background_image) public ImageView mBackgroundImageView;

    public HeroItemLayout(Context context) {
        this(context, null);
    }

    public HeroItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeroItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.hero_item_contents, this, true);
        ButterKnife.inject(this, this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Picasso.with(getContext()).cancelRequest(mBackgroundImageView);
    }

    public void setHeroItem(HeroItem heroItem) {
        mTitleView.setText(heroItem.title);
        mTextView.setText(heroItem.text);
        if (heroItem.textBackgroundColor == 0) {
            mTextView.setBackgroundColor(0);
        } else {
            mTextView.setBackgroundColor(heroItem.textBackgroundColor);
        }
        ImageView imageView = mBackgroundImageView;
        if (heroItem.imageUri != null) {
            Picasso.with(getContext())
                    .load(heroItem.imageUri)
                    .placeholder(R.color.hero_placeholder)
                    .fit()
                    .centerCrop()
                    .into(imageView);
        } else {
            imageView.setBackgroundResource(R.color.hero_placeholder);
        }
    }
}
