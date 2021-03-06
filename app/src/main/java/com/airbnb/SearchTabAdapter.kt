package com.airbnb

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.airbnb.net.HeroItem
import com.airbnb.net.ListItem
import com.airbnb.net.ListingItem
import com.airbnb.ui.HeroItemLayout
import com.airbnb.ui.ListingItemLayout

public class SearchTabAdapter(private val mActivity: MainActivity) : RecyclerView.Adapter<ViewHolder>() {
    companion object {
        private val VIEW_TYPE_HERO = 0
        private val VIEW_TYPE_LISTING = 1
    }

    private var mItems: List<ListItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        @LayoutRes val layoutRes: Int
        when (viewType) {
            VIEW_TYPE_HERO -> layoutRes = R.layout.hero_item
            VIEW_TYPE_LISTING -> layoutRes = R.layout.listing_item
            else -> throw IllegalArgumentException("No layout for viewType " + viewType)
        }
        val inflater = LayoutInflater.from(parent.getContext())
        val view = inflater.inflate(layoutRes, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mItems!!.get(position)

        when (holder.getItemViewType()) {
            VIEW_TYPE_HERO -> {
                val heroItem = item.heroItem as HeroItem
                (holder.itemView as HeroItemLayout).setHeroItem(heroItem)
                holder.itemView.setOnClickListener({mActivity.showHeroItem(heroItem.id)})
            }
            VIEW_TYPE_LISTING -> {
                val listingItem = item.listingItem as ListingItem
                (holder.itemView as ListingItemLayout).setListing(listingItem)
                holder.itemView.setOnClickListener({mActivity.showListing(listingItem.id)})
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = mItems!!.get(position)
        if (item.heroItem != null) {
            return VIEW_TYPE_HERO
        } else if (item.listingItem != null) {
            return VIEW_TYPE_LISTING
        } else {
            throw IllegalArgumentException("Invalid list item at position (" + position + ")")
        }
    }

    override fun getItemCount(): Int {
        return mItems?.size() ?: 0
    }

    public fun setItems(items: List<ListItem>) {
        mItems = items
        notifyDataSetChanged()
    }
}
