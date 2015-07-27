package com.airbnb

import android.content.res.Resources
import android.support.annotation.ColorInt
import android.support.annotation.IntDef

import com.airbnb.net.ListingItem

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

public data class Message(
        public val listingItem: ListingItem,
        public val message: CharSequence,
        public val date: Long,
        public var status: Message.Status) {

    enum class Status {
        NONE, INQUIRY, ACCEPTED, DECLINED
    }

    companion object {

        public fun getStatusText(status: Status): CharSequence {
            when (status) {
                Status.NONE -> return "None"
                Status.INQUIRY -> return "Inquiry"
                Status.ACCEPTED -> return "Accepted"
                Status.DECLINED -> return "Declined"
                else -> throw IllegalArgumentException("Unknown status: " + status)
            }
        }

        ColorInt
        public fun getStatusColor(res: Resources, status: Status): Int {
            when (status) {
                Status.NONE -> return res.getColor(R.color.message_status_none)
                Status.INQUIRY -> return res.getColor(R.color.message_status_inquiry)
                Status.ACCEPTED -> return res.getColor(R.color.message_status_accepted)
                Status.DECLINED -> return res.getColor(R.color.message_status_declined)
                else -> throw IllegalArgumentException("Unknown status: " + status)
            }
        }
    }
}
