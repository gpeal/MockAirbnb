package com.airbnb

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import butterknife.ButterKnife
import butterknife.InjectView

public class HeroFragment : Fragment() {

    InjectView(R.id.text) public var mTextView: TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.hero_fragment, container, false)
        ButterKnife.inject(this, view)

        if (getArguments() == null || !getArguments().containsKey(ARG_HERO_ID)) {
            throw IllegalArgumentException("You must specify a hero id!")
        }

        mTextView.setText("Hero item id " + getArguments().getLong(ARG_HERO_ID))

        return view
    }

    companion object {
        public val ARG_HERO_ID: String = "hero_id"
    }
}
