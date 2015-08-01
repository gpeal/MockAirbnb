package com.airbnb

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.airbnb.net.AirbnbAdapter
import rx.android.schedulers.AndroidSchedulers

public class WishListController (private val mActivity : MainActivity) : ViewController {
    private companion object {
        private val TAG = "WishListController"
    }

    private var mAdapter : WishListAdapter? = null

    override fun initialize(container: ViewGroup): ViewGroup {
        val inflater = LayoutInflater.from(mActivity)
        val rv = inflater.inflate(R.layout.recycler_view, container, false) as RecyclerView
        mAdapter = WishListAdapter(mActivity)
        rv.setAdapter(mAdapter)

        loadWishListTab()

        return rv
    }

    private fun loadWishListTab() {
        AirbnbAdapter.getWishList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({heroItems ->
                mAdapter!!.setItems(heroItems)
            }, {e ->
                Log.e(TAG, "Error loading search tab results!", e)
            })
    }
}