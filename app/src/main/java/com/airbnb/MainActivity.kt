package com.airbnb

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import butterknife.ButterKnife

public class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        getFragmentManager().beginTransaction()
                .replace(R.id.container, MainFragment())
                .addToBackStack(null)
                .commit()
    }

    public fun showListing(id: Long) {
        val frag = ListingFragment()
        val args = Bundle()
        args.putLong(ListingFragment.ARG_LISTING_ID, id)
        frag.setArguments(args)

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.anim.listing_fragment_in,
                        R.anim.listing_fragment_out,
                        R.anim.listing_fragment_in,
                        R.anim.listing_fragment_out)
                .replace(R.id.container, frag)
                .addToBackStack(null)
                .commit()
    }

    public fun showHeroItem(id: Long) {
        val frag = HeroFragment()
        val args = Bundle()
        args.putLong(HeroFragment.ARG_HERO_ID, id)
        frag.setArguments(args)

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.anim.listing_fragment_in,
                        R.anim.listing_fragment_out,
                        R.anim.listing_fragment_in,
                        R.anim.listing_fragment_out)
                .replace(R.id.container, frag)
                .addToBackStack(null)
                .commit()
    }
}
