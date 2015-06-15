package com.airbnb;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;

public class MainActivity extends Activity {
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
        // TODO
    }

    public void showHeroItem(long id) {
        HeroFragment frag = new HeroFragment();
        Bundle args = new Bundle();
        args.putLong(HeroFragment.ARG_HERO_ID, id);
        frag.setArguments(args);

        getFragmentManager().beginTransaction()
                .replace(R.id.container, frag)
                .addToBackStack(null)
                .commit();
    }
}
