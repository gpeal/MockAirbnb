package com.airbnb.ui

import android.content.Context
import android.text.SpannableString
import android.text.format.DateUtils
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView

import com.airbnb.Message
import com.airbnb.R
import com.squareup.picasso.Picasso

public class MessageItemLayout @jvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr) {

    private var mHostImageView: RoundedImageView
    private var mHostNameView: TextView
    private var mMessageView: TextView
    private var mStatusAndListingNameView: TextView
    private var mDateView: TextView

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.message_item_contents, this, true)
        mHostImageView = findViewById(R.id.hostImage) as RoundedImageView
        mHostNameView = findViewById(R.id.host_name) as TextView
        mMessageView = findViewById(R.id.message) as TextView
        mStatusAndListingNameView = findViewById(R.id.status_and_listing_name) as TextView
        mDateView = findViewById(R.id.date) as TextView
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Picasso.with(getContext()).cancelRequest(mHostImageView)
    }

    public fun setMessage(message: Message) {
        mHostNameView.setText(message.listingItem.host)
        mMessageView.setText(message.message)
        mStatusAndListingNameView.setText(getStatusAndListingText(message.status, message.listingItem.title))
        val date = DateUtils.formatDateTime(getContext(), message.date, DateUtils.FORMAT_ABBREV_ALL)
        mDateView.setText(date)

        if (message.listingItem.hostImageUri != null) {
            Picasso.with(getContext()).load(message.listingItem.hostImageUri).fit().centerCrop().into(mHostImageView)
        }
    }

    private fun getStatusAndListingText(status: Message.Status, title: CharSequence): CharSequence {
        val statusText = Message.getStatusText(status)
        val statusColor = Message.getStatusColor(getResources(), status)
        val spannableString = SpannableString("$statusText • $title")
        spannableString.setSpan(ForegroundColorSpan(statusColor), 0, statusText.length(), 0)
        return spannableString
    }
}
