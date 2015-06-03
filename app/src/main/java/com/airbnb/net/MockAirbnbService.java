package com.airbnb.net;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class MockAirbnbService implements AirbnbService {
    @Override
    public Observable<List<ListItem>> getSearchTabItems() {
        List<ListItem> mItems = new ArrayList<>(20);
        mItems.add(new ListItem(new HeroItem("New York", null, null)));
        mItems.add(new ListItem(new Listing("$295", "Albert Park, Australia", null, null)));
        mItems.add(new ListItem(new HeroItem("San Francisco", null, null)));
        mItems.add(new ListItem(new HeroItem("Chicago", null, null)));
        mItems.add(new ListItem(new HeroItem("Washington DC", null, null)));
        mItems.add(new ListItem(new HeroItem("Los Angeles", null, null)));
        return Observable.just(mItems);
    }
}
