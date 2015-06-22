package com.airbnb;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.airbnb.net.AirbnbAdapter;
import com.airbnb.net.HeroItem;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

public class WishListController implements ViewController {
    public static final String TAG = "WishListController";

    private final MainActivity mActivity;

    private WishListAdapter mAdapter;

    public WishListController(MainActivity activity) {
        mActivity = activity;
    }

    @Override
    public ViewGroup initialize(ViewGroup container) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        mAdapter = new WishListAdapter(mActivity);
        rv.setAdapter(mAdapter);

        loadSearchTab();

        return rv;
    }

    private void loadSearchTab() {
        AirbnbAdapter.getInstance().getWishList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<HeroItem>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error loading search tab results!", e);
                    }

                    @Override
                    public void onNext(List<HeroItem> heroItems) {
                        mAdapter.setItems(heroItems);
                    }
                });
    }
}