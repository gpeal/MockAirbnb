package com.airbnb;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagePresenter.ViewHolder> {

    private final MessagePresenter mPresenter = new MessagePresenter();

    private List<Message> mMessages;

    @Override
    public MessagePresenter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mPresenter.createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(MessagePresenter.ViewHolder holder, int position) {
        mPresenter.bindViewHolder(holder, mMessages.get(position));
    }

    @Override
    public int getItemCount() {
        return mMessages == null ? 0 : mMessages.size();
    }

    public void setMessages(List<Message> messages) {
        mMessages = messages;
        notifyDataSetChanged();
    }

}
