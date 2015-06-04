package com.airbnb.net;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class MockAirbnbService implements AirbnbService {
    @Override
    public Observable<List<ListItem>> getSearchTabItems() {
        List<ListItem> mItems = new ArrayList<>(20);
        int textBackgroundColor = 0xffb19a40;
        Uri backgroundUri = Uri.parse("http://sportsplanningguide.com/wp-content/uploads/2013/12/Lake-Tahoe-Sunset-1.png");
        mItems.add(new ListItem(new HeroItem("Lake Tahoe", "Popular Destination", backgroundUri, 0)));
        backgroundUri = Uri.parse("https://newevolutiondesigns.com/images/freebies/los-angeles-santa-monica.jpg");
        mItems.add(new ListItem(new HeroItem("Los Angeles", "Popular Destination", backgroundUri, 0)));
        backgroundUri = Uri.parse("http://www.travellinguide.com/uploads/images/fotogaleri/2015/Mart/21-things-you-can-do-in-san-francisco-for-free.jpg");
        mItems.add(new ListItem(new HeroItem("San Francisco", "Popular Destination", backgroundUri, 0)));
        backgroundUri = Uri.parse("http://img1.wikia.nocookie.net/__cb20131117072042/disney/images/2/2f/NewYorkCity-CATFA.png");
        mItems.add(new ListItem(new HeroItem("New York", "Popular Destination", backgroundUri, 0)));
        mItems.add(new ListItem(new HeroItem("Paris", "4 Listings", backgroundUri, textBackgroundColor)));
        Uri listingUri = Uri.parse("https://a0.muscache.com/ac/pictures/24433197/e593c170_original.jpg?interpolation=lanczos-none&size=x_medium&output-format=jpg&output-quality=70");
        Uri hostUri = Uri.parse("https://a1.muscache.com/ac/users/5283465/profile_pic/1433105369/original.jpg?interpolation=lanczos-none&crop=w:w;*,*&crop=h:h;*,*&resize=68:*&output-format=jpg&output-quality=70");
        mItems.add(new ListItem(new Listing("$295", "Albert Park, Australia", listingUri, hostUri)));
        mItems.add(new ListItem(new HeroItem("San Francisco", null, null, 0)));
        mItems.add(new ListItem(new HeroItem("Chicago", null, null, 0)));
        mItems.add(new ListItem(new HeroItem("Washington DC", null, null, 0)));
        mItems.add(new ListItem(new HeroItem("Los Angeles", null, null, 0)));
        return Observable.just(mItems);
    }
}
