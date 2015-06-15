package com.airbnb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MessagePresenter implements ListPresenter<Message, MessagePresenter.ViewHolder> {

    @Override
    public ViewHolder createViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.message_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, Message message) {
        Context context = holder.itemView.getContext();
        holder.recipientView.setText(message.recipientName);
        holder.messageView.setText(message.message);
        holder.statusView.setText(Message.getStatus(message.status));
        holder.listingNameView.setText(message.listing.title);
        holder.dateView.setText(DateUtils.formatDateTime(context, message.date, DateUtils.FORMAT_ABBREV_ALL));
    }

    @Override
    public void unbindViewHolder(ViewHolder holder) { }

    public static final class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.recipient) public TextView recipientView;
        @InjectView(R.id.message) public TextView messageView;
        @InjectView(R.id.status) public TextView statusView;
        @InjectView(R.id.listing_name) public TextView listingNameView;
        @InjectView(R.id.date) public TextView dateView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
