package com.airbnb;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ListingFragment extends Fragment {
    public static final String ARG_LISTING_ID = "listing_id";

    @InjectView(R.id.text) public TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listing_fragment, container, false);
        ButterKnife.inject(this, view);

        if (getArguments() == null || !getArguments().containsKey(ARG_LISTING_ID)) {
            throw new IllegalArgumentException("You must specify a hero id!");
        }

        mTextView.setText("Hero item id " + getArguments().getLong(ARG_LISTING_ID));

        return view;
    }
}
