package com.airbnb

import android.app.Fragment
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main_fragment.*

public class MainFragment : Fragment() {

    companion object {
        DrawableRes private val TAB_ICON_IDS = intArrayOf(
                R.drawable.icon_tab_search,
                R.drawable.icon_tab_wishlists,
                R.drawable.icon_tab_messages,
                R.drawable.icon_tab_trips)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.setAdapter(mPagerAdapter)
        tabLayout.setupWithViewPager(viewPager)
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        drawerProfileButton.setOnClickListener {
            if (drawerLayout.isDrawerOpen(drawer)) {
                drawerLayout.closeDrawer(drawer)
            } else {
                drawerLayout.openDrawer(drawer)
            }
        }

        // The PagerAdapter only lets you specify titles, not icons so we need to override the tabs here.
        for (i in TAB_ICON_IDS.indices) {
            val tab = tabLayout.getTabAt(i)
            tab.setText(null)
            tab.setIcon(TAB_ICON_IDS[i])
        }
    }

    private val mPagerAdapter = object : PagerAdapter() {

        private val mControllers = arrayOfNulls<ViewController>(TAB_ICON_IDS.size())

        override fun getCount(): Int {
            return TAB_ICON_IDS.size()
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            var controller: ViewController? = mControllers[position]
            if (controller == null) {
                when (TAB_ICON_IDS[position]) {
                    R.drawable.icon_tab_search -> controller = SearchController(getActivity() as MainActivity)
                    R.drawable.icon_tab_wishlists -> controller = WishListController(getActivity() as MainActivity)
                    R.drawable.icon_tab_messages -> controller = MessagesController(getActivity())
                    R.drawable.icon_tab_trips -> controller = TripsController(getActivity())
                    else -> throw IllegalStateException("Unknown TAB_ICON_ID!")
                }
                mControllers[position] = controller
            }

            val view = mControllers[position]!!.initialize(container)
            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            container.removeView(obj as ViewGroup)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return null
        }
    }
}
