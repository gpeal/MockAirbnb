package com.airbnb;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @DrawableRes
    private static final int[] TAB_ICON_IDS = {
            R.drawable.icon_tab_search,
            R.drawable.icon_tab_wishlists,
            R.drawable.icon_tab_messages,
            R.drawable.icon_tab_trips,
    };

    @InjectView(R.id.drawer_layout)
    public DrawerLayout mDrawerLayout;
    @InjectView(R.id.drawer)
    public View mDrawerView;
    @InjectView(R.id.tab_layout)
    public TabLayout mTabLayout;
    @InjectView(R.id.view_pager)
    public ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.inject(this);

        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        // The PagerAdapter only lets you specify titles, not icons so we need to override the tabs
        // here.
        for (int i = 0; i < TAB_ICON_IDS.length; i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setText(null);
            tab.setIcon(TAB_ICON_IDS[i]);
        }
    }

    @OnClick(R.id.drawer_profile_button)
    public void onDrawerProfileButtonClicked() {
        if (mDrawerLayout.isDrawerOpen(mDrawerView)) {
            mDrawerLayout.closeDrawer(mDrawerView);
        } else {
            mDrawerLayout.openDrawer(mDrawerView);
        }
    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {

        private final ViewController[] mControllers = new ViewController[TAB_ICON_IDS.length];

        @Override
        public int getCount() {
            return TAB_ICON_IDS.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ViewController controller = mControllers[position];
            if (controller == null) {
                switch (TAB_ICON_IDS[position]) {
                    case R.drawable.icon_tab_search:
                        controller = new SearchController(MainActivity.this);
                        break;
                    case R.drawable.icon_tab_wishlists:
                        controller = new WishListController(MainActivity.this);
                        break;
                    case R.drawable.icon_tab_messages:
                        controller = new MessagesController(MainActivity.this);
                        break;
                    case R.drawable.icon_tab_trips:
                        controller = new TripsController(MainActivity.this);
                        break;
                    default:
                        throw new IllegalStateException("Unknown TAB_ICON_ID!");
                }
                mControllers[position] = controller;
            }

            ViewGroup view = mControllers[position].initialize(container);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ViewGroup) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }

    };
}
