package com.airbnb

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup

import com.airbnb.net.AirbnbAdapter

import rx.Observer
import rx.android.schedulers.AndroidSchedulers

public class MessagesController(private val mContext: Context) : ViewController {

    private var mAdapter: MessagesAdapter = MessagesAdapter()

    override fun initialize(container: ViewGroup): ViewGroup {
        val inflater = LayoutInflater.from(mContext)
        val rv = inflater.inflate(R.layout.recycler_view, container, false) as RecyclerView
        rv.setAdapter(mAdapter)

        loadMessages()

        return rv
    }



    private fun loadMessages() {
        AirbnbAdapter.getMessages()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({messages ->
                    mAdapter.setMessages(messages)
                })
    }
}
