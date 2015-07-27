package com.airbnb.ui;

import android.content.Context;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.Message;
import com.airbnb.R;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MessageItemLayout extends RelativeLayout {

    @InjectView(R.id.hostImage) RoundedImageView mHostImageView;
    @InjectView(R.id.host_name) TextView mHostNameView;
    @InjectView(R.id.message) TextView mMessageView;
    @InjectView(R.id.status_and_listing_name) TextView mStatusAndListingNameView;
    @InjectView(R.id.date) TextView mDateView;


    public MessageItemLayout(Context context) {
        this(context, null);
    }

    public MessageItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MessageItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.message_item_contents, this, true);
        ButterKnife.inject(this, this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Picasso.with(getContext()).cancelRequest(mHostImageView);
    }

    public void setMessage(Message message) {
        mHostNameView.setText(message.getListingItem().host);
        mMessageView.setText(message.getMessage());
        mStatusAndListingNameView.setText(getStatusAndListingText(message.getStatus(), message.getListingItem().title));
        String date = DateUtils.formatDateTime(getContext(), message.getDate(), DateUtils.FORMAT_ABBREV_ALL);
        mDateView.setText(date);

        if (message.getListingItem().hostImageUri != null) {
            Picasso.with(getContext())
                    .load(message.getListingItem().hostImageUri)
                    .fit()
                    .centerCrop()
                    .into(mHostImageView);
        }
    }

    private CharSequence getStatusAndListingText(Message.Status status, CharSequence title) {
        CharSequence statusText = Message.Companion.getStatusText(status);
        int statusColor = Message.Companion.getStatusColor(getResources(), status);
        SpannableString spannableString = new SpannableString(statusText + " • " + title);
        spannableString.setSpan(new ForegroundColorSpan(statusColor), 0, statusText.length(), 0);
        return spannableString;
    }
}
