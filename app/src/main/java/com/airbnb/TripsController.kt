package com.airbnb

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup

public class TripsController(private val mContext: Context) : ViewController {

    override fun initialize(container: ViewGroup): ViewGroup {
        val inflater = LayoutInflater.from(mContext)
        val view = inflater.inflate(R.layout.recycler_view, container, false) as ViewGroup
        view.setBackgroundColor(Color.BLUE)
        return view
    }


}