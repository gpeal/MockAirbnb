package com.airbnb.net;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.IntDef;

import com.airbnb.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Listing {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ROOM_TYPE_PRIVATE_ROOM, ROOM_TYPE_ENTIRE_HOME})
    public @interface RoomType {}
    public static final int ROOM_TYPE_PRIVATE_ROOM = 1;
    public static final int ROOM_TYPE_ENTIRE_HOME = 2;

    public final CharSequence title;
    public final CharSequence location;
    public final Uri[] images;
    public final boolean isStarred;
    public final CharSequence price;
    public final boolean isInstantBookable;
    public final float rating;
    public final int numberOfReviews;
    public final Uri hostImage;
    public final CharSequence hostName;
    @RoomType public final int roomType;
    public final int numberOfGuests;
    public final int numberOfBedrooms;
    public final int numberOfBeds;
    public final Uri topReviewImage;
    public final CharSequence topReviewName;
    public final long topReviewDate;
    public final CharSequence topReviewReview;
    public final float lat;
    public final float lng;
    public final CharSequence address;

    public Listing(
            CharSequence title,
            CharSequence location,
            Uri[] images,
            boolean isStarred,
            CharSequence price,
            boolean isInstantBookable,
            float rating,
            int numberOfReviews,
            Uri hostImage,
            CharSequence hostName,
            @RoomType int roomType,
            int numberOfGuests,
            int numberOfBedrooms,
            int numberOfBeds,
            Uri topReviewImage,
            CharSequence topReviewName,
            long topReviewDate,
            CharSequence topReviewReview,
            float lat,
            float lng,
            CharSequence address) {
        this.title = title;
        this.location = location;
        this.images = images;
        this.isStarred = isStarred;
        this.price = price;
        this.isInstantBookable = isInstantBookable;
        this.rating = rating;
        this.numberOfReviews = numberOfReviews;
        this.hostImage = hostImage;
        this.hostName = hostName;
        this.roomType = roomType;
        this.numberOfGuests = numberOfGuests;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBeds = numberOfBeds;
        this.topReviewImage = topReviewImage;
        this.topReviewName = topReviewName;
        this.topReviewDate = topReviewDate;
        this.topReviewReview = topReviewReview;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
    }

    public CharSequence getRoomTypeString(Context context) {
        switch(roomType) {
            case ROOM_TYPE_PRIVATE_ROOM:
                return context.getString(R.string.private_room);
            case ROOM_TYPE_ENTIRE_HOME:
                return context.getString(R.string.entire_home);
            default:
                throw new IllegalArgumentException("Unknown room type (" + roomType + ")");
        }
    }
}
