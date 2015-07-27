package com.airbnb

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import kotlinx.android.synthetic.hero_fragment.*

public class HeroFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (getArguments() == null || !getArguments().containsKey(ARG_HERO_ID)) {
            throw IllegalArgumentException("You must specify a hero id!")
        }
        return inflater!!.inflate(R.layout.hero_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text.setText("Hero item id " + getArguments().getLong(ARG_HERO_ID))
    }

    companion object {
        public val ARG_HERO_ID: String = "hero_id"
    }
}
