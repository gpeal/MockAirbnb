package com.airbnb.net;

import android.net.Uri;
import android.text.format.DateUtils;

import com.airbnb.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit.http.Path;
import rx.Observable;

public class MockAirbnbService implements AirbnbService {
    @Override
    public Observable<List<ListItem>> getSearchTabItems() {
        List<ListItem> items = new ArrayList<>();
        int textBackgroundColor = 0xffb19a40;
        Uri backgroundUri = Uri.parse("http://sportsplanningguide.com/wp-content/uploads/2013/12/Lake-Tahoe-Sunset-1.png");
        items.add(new ListItem(new HeroItem(1, "Lake Tahoe", "Popular Destination", backgroundUri, 0)));
        Uri listingUri = Uri.parse("https://a0.muscache.com/ac/pictures/24433197/e593c170_original.jpg?interpolation=lanczos-none&size=x_medium&output-format=jpg&output-quality=70");
        Uri hostUri = Uri.parse("https://a1.muscache.com/ac/users/5283465/profile_pic/1433105369/original.jpg?interpolation=lanczos-none&crop=w:w;*,*&crop=h:h;*,*&resize=68:*&output-format=jpg&output-quality=70");
        items.add(new ListItem(new ListingItem(6, "Bryan", "$295", "Albert Park, Australia", listingUri, hostUri)));
        backgroundUri = Uri.parse("https://newevolutiondesigns.com/images/freebies/los-angeles-santa-monica.jpg");
        items.add(new ListItem(new HeroItem(2, "Los Angeles", "Popular Destination", backgroundUri, 0)));
        backgroundUri = Uri.parse("http://www.travellinguide.com/uploads/images/fotogaleri/2015/Mart/21-things-you-can-do-in-san-francisco-for-free.jpg");
        items.add(new ListItem(new HeroItem(3, "San Francisco", "Popular Destination", backgroundUri, 0)));
        backgroundUri = Uri.parse("http://img1.wikia.nocookie.net/__cb20131117072042/disney/images/2/2f/NewYorkCity-CATFA.png");
        items.add(new ListItem(new HeroItem(4, "New York", "Popular Destination", backgroundUri, 0)));
        items.add(new ListItem(new HeroItem(5, "Paris", "4 Listings", backgroundUri, textBackgroundColor)));
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

        String title = "John Steinbeck's Cottage";
        String description = "John Steinbeck's Cottage";
        Uri listingtUri = Uri.parse("https://a0.muscache.com/ac/pictures/24433197/e593c170_original.jpg?interpolation=lanczos-none&size=x_medium&output-format=jpg&output-quality=70");
        Uri hostUri = Uri.parse("https://a1.muscache.com/ac/users/5283465/profile_pic/1433105369/original.jpg?interpolation=lanczos-none&crop=w:w;*,*&crop=h:h;*,*&resize=68:*&output-format=jpg&output-quality=70");
        ListingItem listingItem = new ListingItem(1, "Kevin & Vicky", title, description, listingtUri, hostUri);
        long date = System.currentTimeMillis() - DateUtils.DAY_IN_MILLIS;
        String message = "Just wanted to touch base to make sure you guys arrived safely last night and that you have settled in nicely";
        messages.add(new Message(listingItem, message, date, Message.STATUS_ACCEPTED));
        return Observable.just(messages);
    }

    @Override
    public Observable<Listing> getListing(@Path("id") long id) {
        return Observable.just(new Listing(
                "John Steinbeck's Cottage" /** Title */,
                "Pacific Grove, CA" /** location */,
                new Uri[] {
                        Uri.parse("https://a0.muscache.com/ac/pictures/32061655/9792844b_original.jpg?interpolation=lanczos-none&size=large&output-format=jpg&output-quality=70"),
                        Uri.parse("https://a0.muscache.com/ac/pictures/32061843/a454953d_original.jpg?interpolation=lanczos-none&size=large&output-format=jpg&output-quality=70")
                } /** images */,
                false /** isStarred */,
                "$195" /** price */,
                true /** instant bookable */,
                4.1f /** rating */,
                69 /** number of reviews */,
                Uri.parse("https://a2.muscache.com/ac/users/5517174/profile_pic/1427385973/original.jpg?interpolation=lanczos-none&crop=w:w;*,*&crop=h:h;*,*&resize=225:*&output-format=jpg&output-quality=70") /** host image */,
                "Kevin & Vicky" /** host name */,
                Listing.ROOM_TYPE_ENTIRE_HOME /** room type */,
                5 /** number of guests */,
                1 /** number of bedrooms */,
                1 /** number of beds */,
                Uri.parse("https://a2.muscache.com/ac/users/4346177/profile_pic/1354866284/original.jpg?interpolation=lanczos-none&crop=w:w;*,*&crop=h:h;*,*&resize=225:*&output-format=jpg&output-quality=70") /** top review image */,
                "Nina" /** top review name */,
                new GregorianCalendar(2015, 06, 01).getTimeInMillis() /** top review date */,
                "We had the best time at the Steinbeck cottage. The place is really very very nice, " +
                        "great attention to every detail, very clean and everything you could need " +
                        "was provided. Thank you Kevin and Vicky, your place is amazing and we had " +
                        "a great time!" /** top review message */,
                36.621019f /** lat */,
                -121.921040f /** lng */,
                "Eardley Avenue, Pacific Grove, CA 93950, United States"));
    }
}
