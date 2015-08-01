package com.airbnb

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup

import com.airbnb.net.AirbnbAdapter
import com.airbnb.net.ListItem

import rx.Observer
import rx.android.schedulers.AndroidSchedulers

public class SearchController(private val mActivity: MainActivity) : ViewController {

    private var mAdapter: SearchTabAdapter? = null

    override fun initialize(container: ViewGroup): ViewGroup {
        val inflater = LayoutInflater.from(mActivity)
        val rv = inflater.inflate(R.layout.recycler_view, container, false) as RecyclerView
        mAdapter = SearchTabAdapter(mActivity)
        rv.setAdapter(mAdapter)

        loadSearchTab()

        return rv
    }

    private fun loadSearchTab() {
        AirbnbAdapter.getSearchTabItems()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({listItems -> mAdapter!!.setItems(listItems)})
    }
}
