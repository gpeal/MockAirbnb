package com.airbnb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.net.AirbnbAdapter;
import com.airbnb.net.Listing;
import com.airbnb.ui.TextUtils;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;


public class ListingFragment extends Fragment {
    private static final String TAG = "ListingFragment";
    public static final String ARG_LISTING_ID = "listing_id";

    @InjectView(R.id.toolbar) public Toolbar mToolbar;
    @InjectView(R.id.listing_image) public ImageView mListingImageView;
    @InjectView(R.id.price) public TextView mPriceView;
    @InjectView(R.id.listing_name) public TextView mListingNameView;
    @InjectView(R.id.star_1) public ImageView mStar1;
    @InjectView(R.id.star_2) public ImageView mStar2;
    @InjectView(R.id.star_3) public ImageView mStar3;
    @InjectView(R.id.star_4) public ImageView mStar4;
    @InjectView(R.id.star_5) public ImageView mStar5;
    @InjectView(R.id.num_reviews) public TextView mNumReviewsView;
    @InjectView(R.id.host_image) public ImageView mHostImageView;
    @InjectView(R.id.room_type) public TextView mRoomTypeView;
    @InjectView(R.id.hosted_by) public TextView mHostedByView;
    @InjectView(R.id.number_of_guests) public TextView mNumberOfGuestsView;
    @InjectView(R.id.number_of_beds) public TextView mNumberOfBedsView;
    @InjectView(R.id.number_of_bedrooms) public TextView mNumberOfBedroomsView;
    @InjectView(R.id.number_of_reviews) public TextView mNumberOfReviewsView;
    @InjectView(R.id.reviewer_image) public ImageView mReviewerImageView;
    @InjectView(R.id.reviewer_name) public TextView mReviewerNameView;
    @InjectView(R.id.review_date) public TextView mReviewDateView;
    @InjectView(R.id.review) public TextView mReviewView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listing_fragment, container, false);
        ButterKnife.inject(this, view);

        if (getArguments() == null || !getArguments().containsKey(ARG_LISTING_ID)) {
            throw new IllegalArgumentException("You must specify a hero id!");
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();Picasso.with(getActivity())
                .cancelRequest(mListingImageView);
        Picasso.with(getActivity())
                .cancelRequest(mHostImageView);
    }

    private void bindListing(Listing listing) {
        mToolbar.setTitle(listing.title);
        mToolbar.setSubtitle(listing.location);
        Picasso.with(getActivity())
                .load(listing.images[0])
                .placeholder(R.color.background_light_grey)
                .fit()
                .centerCrop()
                .into(mListingImageView);
        mPriceView.setText(TextUtils.formatPrice(listing.price));
        mListingNameView.setText(listing.title);
        mStar1.setActivated(listing.rating >= 1);
        mStar2.setActivated(listing.rating >= 2);
        mStar3.setActivated(listing.rating >= 3);
        mStar4.setActivated(listing.rating >= 4);
        mStar5.setActivated(listing.rating >= 4.8);
        mNumReviewsView.setText("" + listing.numberOfReviews);
        Picasso.with(getActivity())
                .load(listing.hostImage)
                .placeholder(R.color.background_light_grey)
                .fit()
                .centerCrop()
                .into(mHostImageView);
        mRoomTypeView.setText(listing.getRoomTypeString(getActivity()));
        mHostedByView.setText(getActivity().getString(R.string.hosted_by, listing.hostName));
        mNumberOfGuestsView.setText(getActivity().getString(R.string.number_of_guests, listing.numberOfGuests));
        mNumberOfBedroomsView.setText(getActivity().getString(R.string.number_of_bedrooms, listing.numberOfBedrooms));
        mNumberOfBedsView.setText(getActivity().getString(R.string.number_of_beds, listing.numberOfBeds));
        mNumberOfReviewsView.setText(getResources().getQuantityString(R.plurals.number_of_reviews, listing.numberOfReviews, listing.numberOfReviews));
        Picasso.with(getActivity())
                .load(listing.topReviewImage)
                .placeholder(R.color.background_light_grey)
                .fit()
                .centerCrop()
                .into(mReviewerImageView);
        mReviewerNameView.setText(listing.topReviewName);
        CharSequence date = DateFormat.format("MMM yyyy", listing.topReviewDate);
        mReviewDateView.setText(date);
        mReviewView.setText(listing.topReviewReview);
    }
}
