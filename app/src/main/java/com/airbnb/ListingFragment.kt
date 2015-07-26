package com.airbnb

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.airbnb.net.AirbnbAdapter
import com.airbnb.net.Listing
import com.airbnb.ui.TextUtils
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.listing_fragment.*

import rx.Observer
import rx.android.schedulers.AndroidSchedulers


public class ListingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (getArguments() == null || !getArguments().containsKey(ARG_LISTING_ID)) {
            throw IllegalArgumentException("You must specify a hero id!")
        }
        return inflater!!.inflate(R.layout.listing_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = getActivity() as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        activity.getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener({
                getActivity().getSupportFragmentManager().popBackStack()
        })

        val id = getArguments().getLong(ARG_LISTING_ID)
        AirbnbAdapter.getInstance().getListing(id).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Listing> {
                    override fun onCompleted() { }

                    override fun onError(e: Throwable) {
                        Toast.makeText(getActivity(), getString(R.string.error_loading_listing), Toast.LENGTH_SHORT)
                        Log.e(TAG, "Error loading listing with id " + id, e)
                    }

                    override fun onNext(listing: Listing) {
                        bindListing(listing)
                    }
                })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Picasso.with(getActivity()).cancelRequest(listingImage)
        Picasso.with(getActivity()).cancelRequest(hostImage)
    }

    private fun bindListing(listing: Listing) {
        toolbar.setTitle(listing.title)
        toolbar.setSubtitle(listing.location)
        Picasso.with(getActivity()).load(listing.images[0]).placeholder(R.color.background_light_grey).fit().centerCrop().into(listingImage)
        price.setText(TextUtils.formatPrice(listing.price))
        listingName.setText(listing.title)
        star1.setActivated(listing.rating >= 1)
        star2.setActivated(listing.rating >= 2)
        star3.setActivated(listing.rating >= 3)
        star4.setActivated(listing.rating >= 4)
        star5.setActivated(listing.rating >= 4.8)
        numReviews.setText("" + listing.numberOfReviews)
        Picasso.with(getActivity()).load(listing.hostImage).placeholder(R.color.background_light_grey).fit().centerCrop().into(hostImage)
        roomType.setText(listing.getRoomTypeString(getActivity()))
        hostedBy.setText(getActivity().getString(R.string.hosted_by, listing.hostName))
        numberOfGuests.setText(getActivity().getString(R.string.number_of_guests, listing.numberOfGuests))
        numberOfBedrooms.setText(getActivity().getString(R.string.number_of_bedrooms, listing.numberOfBedrooms))
        numberOfBeds.setText(getActivity().getString(R.string.number_of_beds, listing.numberOfBeds))
        numReviews.setText(getResources().getQuantityString(R.plurals.number_of_reviews, listing.numberOfReviews, listing.numberOfReviews))
        Picasso.with(getActivity()).load(listing.topReviewImage).placeholder(R.color.background_light_grey).fit().centerCrop().into(reviewerImage)
        reviewerName.setText(listing.topReviewName)
        val date = DateFormat.format("MMM yyyy", listing.topReviewDate)
        reviewDate.setText(date)
        review.setText(listing.topReviewReview)
    }

    companion object {
        private val TAG = "ListingFragment"
        public val ARG_LISTING_ID: String = "listing_id"
    }
}
