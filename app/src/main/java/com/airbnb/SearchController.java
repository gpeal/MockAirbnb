package com.airbnb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.airbnb.net.AirbnbAdapter;
import com.airbnb.net.ListItem;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

public class SearchController implements ViewController {
    private static final String TAG = "SearchController";

    private final Context mContext;

    private SearchTabAdapter mAdapter;

    public SearchController(Context context) {
        mContext = context;
    }

    @Override
    public ViewGroup initialize(ViewGroup container) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        mAdapter = new SearchTabAdapter();
        rv.setAdapter(mAdapter);

        loadSearchTab();

        return rv;
    }

    private void loadSearchTab() {
        AirbnbAdapter.getInstance().getSearchTabItems()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ListItem>>() {
                    @Override
                    public void onCompleted() { }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error loading search tab results!", e);
                    }

                    @Override
                    public void onNext(List<ListItem> listItems) {
                        mAdapter.setItems(listItems);
                    }
                });
    }
}
