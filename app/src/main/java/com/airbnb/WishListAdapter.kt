package com.airbnb

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.airbnb.net.HeroItem
import com.airbnb.ui.HeroItemLayout

public class WishListAdapter(private val mActivity: MainActivity) : RecyclerView.Adapter<ViewHolder>() {

    private var mItems: List<HeroItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.getContext())
        val view = inflater.inflate(R.layout.hero_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val heroItem = mItems!!.get(position)
        (holder.itemView as HeroItemLayout).setHeroItem(heroItem)
        holder.itemView.setOnClickListener({mActivity.showHeroItem(heroItem.id)})
    }

    override fun getItemCount(): Int {
        return if (mItems == null) 0 else mItems!!.size()
    }

    public fun setItems(items: List<HeroItem>) {
        mItems = items
        notifyDataSetChanged()
    }
}
