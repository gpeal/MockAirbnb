package com.airbnb

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.airbnb.ui.MessageItemLayout

public class MessagesAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var mMessages: List<Message>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.getContext())
        val view = inflater.inflate(R.layout.message_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder.itemView as MessageItemLayout).setMessage(mMessages!!.get(position))
    }

    override fun getItemCount(): Int {
        return mMessages?.size() ?: 0
    }

    public fun setMessages(messages: List<Message>) {
        mMessages = messages
        notifyDataSetChanged()
    }
}
