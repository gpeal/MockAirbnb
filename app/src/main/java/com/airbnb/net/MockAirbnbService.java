package com.airbnb.net;

import android.net.Uri;
import android.os.SystemClock;
import android.text.format.DateUtils;

import com.airbnb.Message;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class MockAirbnbService implements AirbnbService {
    @Override
    public Observable<List<ListItem>> getSearchTabItems() {
        List<ListItem> items = new ArrayList<>();
        int textBackgroundColor = 0xffb19a40;
        Uri backgroundUri = Uri.parse("http://sportsplanningguide.com/wp-content/uploads/2013/12/Lake-Tahoe-Sunset-1.png");
        items.add(new ListItem(new HeroItem(1, "Lake Tahoe", "Popular Destination", backgroundUri, 0)));
        backgroundUri = Uri.parse("https://newevolutiondesigns.com/images/freebies/los-angeles-santa-monica.jpg");
        items.add(new ListItem(new HeroItem(2, "Los Angeles", "Popular Destination", backgroundUri, 0)));
        backgroundUri = Uri.parse("http://www.travellinguide.com/uploads/images/fotogaleri/2015/Mart/21-things-you-can-do-in-san-francisco-for-free.jpg");
        items.add(new ListItem(new HeroItem(3, "San Francisco", "Popular Destination", backgroundUri, 0)));
        backgroundUri = Uri.parse("http://img1.wikia.nocookie.net/__cb20131117072042/disney/images/2/2f/NewYorkCity-CATFA.png");
        items.add(new ListItem(new HeroItem(4, "New York", "Popular Destination", backgroundUri, 0)));
        items.add(new ListItem(new HeroItem(5, "Paris", "4 Listings", backgroundUri, textBackgroundColor)));
        Uri listingUri = Uri.parse("https://a0.muscache.com/ac/pictures/24433197/e593c170_original.jpg?interpolation=lanczos-none&size=x_medium&output-format=jpg&output-quality=70");
        Uri hostUri = Uri.parse("https://a1.muscache.com/ac/users/5283465/profile_pic/1433105369/original.jpg?interpolation=lanczos-none&crop=w:w;*,*&crop=h:h;*,*&resize=68:*&output-format=jpg&output-quality=70");
        items.add(new ListItem(new Listing(6, "$295", "Albert Park, Australia", listingUri, hostUri)));
        items.add(new ListItem(new HeroItem(7, "San Francisco", null, null, 0)));
        items.add(new ListItem(new HeroItem(8, "Chicago", null, null, 0)));
        items.add(new ListItem(new HeroItem(9, "Washington DC", null, null, 0)));
        items.add(new ListItem(new HeroItem(10, "Los Angeles", null, null, 0)));
        return Observable.just(items);
    }

    @Override
    public Observable<List<HeroItem>> getWishList() {
        int textBackgroundColor = 0xffb19a40;
        Uri uri = Uri.parse("https://a0.muscache.com/ac/pictures/24433197/e593c170_original.jpg?interpolation=lanczos-none&size=x_medium&output-format=jpg&output-quality=70");
        List<HeroItem> items = new ArrayList<>();
        items.add(new HeroItem(1, "Mobile Starred Listings", "2 Listings - Private", uri, textBackgroundColor));
        items.add(new HeroItem(2, "Dream Homes", "0 Listings", uri, textBackgroundColor));
        items.add(new HeroItem(3, "Vacation Places", "0 Listings", uri, textBackgroundColor));
        return Observable.just(items);
    }

    @Override
    public Observable<List<Message>> getMessages() {
        List<Message> messages = new ArrayList<>();

        Uri listingUri = Uri.parse("https://a0.muscache.com/ac/pictures/24433197/e593c170_original.jpg?interpolation=lanczos-none&size=x_medium&output-format=jpg&output-quality=70");
        Listing listing = new Listing(1, "Charming Cottage", "In the heart of Montreal", listingUri, listingUri);
        long date = SystemClock.currentThreadTimeMillis() - DateUtils.DAY_IN_MILLIS;
        messages.add(new Message(listing, "I would love to stay in your place", "Bryan", date, Message.STATUS_INQUIRY));

        return Observable.just(messages);
    }
}
