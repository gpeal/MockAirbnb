package com.airbnb;

import android.content.res.Resources;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;

import com.airbnb.net.Listing;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Message {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STATUS_NONE, STATUS_INQUIRY})
    public @interface Status {}
    public static final int STATUS_NONE = 0;
    public static final int STATUS_INQUIRY = 1;
    public static final int STATUS_ACCEPTED = 2;
    public static final int STATUS_DECLINED = 3;

    public Listing listing;
    public CharSequence message;
    public long date;
    public @Status int status;

    public Message(Listing listing, CharSequence message, long date, @Status int status) {
        this.listing = listing;
        this.message = message;
        this.date = date;
        this.status = status;
    }

    public static CharSequence getStatusText(@Status int status) {
        switch(status) {
            case STATUS_NONE:
                return "None";
            case STATUS_INQUIRY:
                return "Inquiry";
            case STATUS_ACCEPTED:
                return "Accepted";
            case STATUS_DECLINED:
                return "Declined";
            default:
                throw new IllegalArgumentException("Unknown status: " + status);
        }
    }

    @ColorInt
    public static int getStatusColor(Resources res, @Status int status){
        switch(status) {
            case STATUS_NONE:
                return res.getColor(R.color.message_status_none);
            case STATUS_INQUIRY:
                return res.getColor(R.color.message_status_inquiry);
            case STATUS_ACCEPTED:
                return res.getColor(R.color.message_status_accepted);
            case STATUS_DECLINED:
                return res.getColor(R.color.message_status_declined);
            default:
                throw new IllegalArgumentException("Unknown status: " + status);
        }
    }
}
