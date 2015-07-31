package com.airbnb.net

import android.content.Context
import android.net.Uri
import android.support.annotation.IntDef

import com.airbnb.R

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

public class Listing(
        val title: CharSequence,
        val location: CharSequence,
        val images: Array<Uri>,
        val isStarred: Boolean,
        val price: CharSequence,
        val isInstantBookable: Boolean,
        val rating: Float,
        val numberOfReviews: Int,
        val hostImage: Uri,
        val hostName: CharSequence,
        Listing.RoomType val roomType: Int,
        val numberOfGuests: Int,
        val numberOfBedrooms: Int,
        val numberOfBeds: Int,
        val topReviewImage: Uri,
        val topReviewName: CharSequence,
        val topReviewDate: Long,
        val topReviewReview: CharSequence,
        val lat: Float,
        val lng: Float,
        val address: CharSequence) {

    companion object {
        public val ROOM_TYPE_PRIVATE_ROOM: Int = 1
        public val ROOM_TYPE_ENTIRE_HOME: Int = 2
    }

    Retention(RetentionPolicy.SOURCE)
    IntDef(ROOM_TYPE_PRIVATE_ROOM.toLong(), ROOM_TYPE_ENTIRE_HOME.toLong())
    annotation public class RoomType

    public fun getRoomTypeString(context: Context): CharSequence {
        when (roomType) {
            ROOM_TYPE_PRIVATE_ROOM -> return context.getString(R.string.private_room)
            ROOM_TYPE_ENTIRE_HOME -> return context.getString(R.string.entire_home)
            else -> throw IllegalArgumentException("Unknown room type (" + roomType + ")")
        }
    }
}
