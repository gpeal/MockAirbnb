package com.airbnb;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.net.AirbnbAdapter;
import com.airbnb.net.Listing;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

public class ListingFragment extends Fragment {
    private static final String TAG = "ListingFragment";
    public static final String ARG_LISTING_ID = "listing_id";

    @InjectView(R.id.toolbar) public Toolbar mToolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listing_fragment, container, false);
        ButterKnife.inject(this, view);

        if (getArguments() == null || !getArguments().containsKey(ARG_LISTING_ID)) {
            throw new IllegalArgumentException("You must specify a hero id!");
        }

        mToolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        final long id = getArguments().getLong(ARG_LISTING_ID);
        AirbnbAdapter.getInstance().getListing(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Listing>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), getString(R.string.error_loading_listing), Toast.LENGTH_SHORT);
                        Log.e(TAG, "Error loading listing with id " + id, e);
                    }

                    @Override
                    public void onNext(Listing listing) {
                        bindListing(listing);
                    }
                });

        return view;
    }

    private void bindListing(Listing listing) {
        mToolbar.setTitle(listing.title);
        mToolbar.setSubtitle(listing.location);
    }
}
