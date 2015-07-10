package com.airbnb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.inject(this);

        getFragmentManager().beginTransaction()
                .replace(R.id.container, new MainFragment())
                .addToBackStack(null)
                .commit();
    }

    public void showListing(long id) {
        ListingFragment frag = new ListingFragment();
        Bundle args = new Bundle();
        args.putLong(ListingFragment.ARG_LISTING_ID, id);
        frag.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.listing_fragment_in, R.anim.listing_fragment_out,
                        R.anim.listing_fragment_in, R.anim.listing_fragment_out)
                .replace(R.id.container, frag)
                .addToBackStack(null)
                .commit();
    }

    public void showHeroItem(long id) {
        HeroFragment frag = new HeroFragment();
        Bundle args = new Bundle();
        args.putLong(HeroFragment.ARG_HERO_ID, id);
        frag.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.listing_fragment_in, R.anim.listing_fragment_out,
                        R.anim.listing_fragment_in, R.anim.listing_fragment_out)
                .replace(R.id.container, frag)
                .addToBackStack(null)
                .commit();
    }
}
