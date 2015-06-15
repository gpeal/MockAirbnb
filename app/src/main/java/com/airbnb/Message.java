package com.airbnb;

import android.support.annotation.IntDef;

import com.airbnb.net.Listing;

public class Message {

    @IntDef({STATUS_NONE, STATUS_INQUIRY})
    private @interface Status {}
    public static final int STATUS_NONE = 0;
    public static final int STATUS_INQUIRY = 1;
    public static final int STATUS_ACCEPTED = 2;
    public static final int STATUS_DECLINED = 3;

    public Listing listing;
    public CharSequence message;
    public CharSequence recipientName;
    public long date;
    public @Status int status;

    public Message(Listing listing, CharSequence message, CharSequence recipientName, long date, @Status int status) {
        this.listing = listing;
        this.message = message;
        this.recipientName = recipientName;
        this.date = date;
        this.status = status;
    }

    public static CharSequence getStatus(@Status int status) {
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
}
