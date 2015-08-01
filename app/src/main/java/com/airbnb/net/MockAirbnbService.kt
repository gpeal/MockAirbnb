package com.airbnb.net

import android.net.Uri
import android.text.format.DateUtils

import com.airbnb.Message

import java.util.ArrayList
import java.util.GregorianCalendar

import retrofit.http.Path
import rx.Observable

public class MockAirbnbService : AirbnbService {
    override fun getSearchTabItems(): Observable<MutableList<ListItem>> {
        val items = ArrayList<ListItem>()
        val textBackgroundColor = -5137856
        val listingUri = Uri.parse("https://a0.muscache.com/ac/pictures/24433197/e593c170_original.jpg?interpolation=lanczos-none&size=x_medium&output-format=jpg&output-quality=70")
        val hostUri = Uri.parse("https://a2.muscache.com/ac/users/5517174/profile_pic/1427385973/original.jpg?interpolation=lanczos-none&crop=w:w;*,*&crop=h:h;*,*&resize=225:*&output-format=jpg&output-quality=70")
        items.add(ListItem(ListingItem(6, "Kevin & Vicky", "$195", "Pacific Grove, CA", listingUri, hostUri)))
        var backgroundUri = Uri.parse("http://sportsplanningguide.com/wp-content/uploads/2013/12/Lake-Tahoe-Sunset-1.png")
        items.add(ListItem(HeroItem(1, "Lake Tahoe", "Popular Destination", backgroundUri, 0)))
        backgroundUri = Uri.parse("https://newevolutiondesigns.com/images/freebies/los-angeles-santa-monica.jpg")
        items.add(ListItem(HeroItem(2, "Los Angeles", "Popular Destination", backgroundUri, 0)))
        backgroundUri = Uri.parse("http://www.travellinguide.com/uploads/images/fotogaleri/2015/Mart/21-things-you-can-do-in-san-francisco-for-free.jpg")
        items.add(ListItem(HeroItem(3, "San Francisco", "Popular Destination", backgroundUri, 0)))
        backgroundUri = Uri.parse("http://img1.wikia.nocookie.net/__cb20131117072042/disney/images/2/2f/NewYorkCity-CATFA.png")
        items.add(ListItem(HeroItem(4, "New York", "Popular Destination", backgroundUri, 0)))
        items.add(ListItem(HeroItem(5, "Paris", "4 Listings", backgroundUri, textBackgroundColor)))
        items.add(ListItem(HeroItem(7, "San Francisco", null, null, 0)))
        items.add(ListItem(HeroItem(8, "Chicago", null, null, 0)))
        items.add(ListItem(HeroItem(9, "Washington DC", null, null, 0)))
        items.add(ListItem(HeroItem(10, "Los Angeles", null, null, 0)))
        return Observable.just<MutableList<ListItem>>(items)
    }

    override fun getWishList(): Observable<MutableList<HeroItem>> {
        val textBackgroundColor = -5137856
        val uri = Uri.parse("https://a0.muscache.com/ac/pictures/24433197/e593c170_original.jpg?interpolation=lanczos-none&size=x_medium&output-format=jpg&output-quality=70")
        val items = ArrayList<HeroItem>()
        items.add(HeroItem(1, "Mobile Starred Listings", "2 Listings - Private", uri, textBackgroundColor))
        items.add(HeroItem(2, "Dream Homes", "0 Listings", uri, textBackgroundColor))
        items.add(HeroItem(3, "Vacation Places", "0 Listings", uri, textBackgroundColor))
        return Observable.just<MutableList<HeroItem>>(items)
    }

    override fun getMessages(): Observable<MutableList<Message>> {
        val messages = ArrayList<Message>()

        val title = "John Steinbeck's Cottage"
        val description = "John Steinbeck's Cottage"
        val listingtUri = Uri.parse("https://a0.muscache.com/ac/pictures/24433197/e593c170_original.jpg?interpolation=lanczos-none&size=x_medium&output-format=jpg&output-quality=70")
        val hostUri = Uri.parse("https://a1.muscache.com/ac/users/5283465/profile_pic/1433105369/original.jpg?interpolation=lanczos-none&crop=w:w;*,*&crop=h:h;*,*&resize=68:*&output-format=jpg&output-quality=70")
        val listingItem = ListingItem(1, "Kevin & Vicky", title, description, listingtUri, hostUri)
        val date = System.currentTimeMillis() - DateUtils.DAY_IN_MILLIS
        val message = "Just wanted to touch base to make sure you guys arrived safely last night and that you have settled in nicely"
        messages.add(Message(listingItem, message, date, Message.Status.ACCEPTED))
        return Observable.just<MutableList<Message>>(messages)
    }

    override fun getListing(Path("id") id: Long): Observable<Listing> {
        return Observable.just(Listing("John Steinbeck's Cottage"
                /** Title  */
                , "Pacific Grove, CA"
                /** location  */
                , arrayOf(Uri.parse("https://a0.muscache.com/ac/pictures/32061655/9792844b_original.jpg?interpolation=lanczos-none&size=large&output-format=jpg&output-quality=70"), Uri.parse("https://a0.muscache.com/ac/pictures/32061843/a454953d_original.jpg?interpolation=lanczos-none&size=large&output-format=jpg&output-quality=70"))
                /** images  */
                , false
                /** isStarred  */
                , "$195"
                /** price  */
                , true
                /** instant bookable  */
                , 4.1f
                /** rating  */
                , 69
                /** number of reviews  */
                , Uri.parse("https://a2.muscache.com/ac/users/5517174/profile_pic/1427385973/original.jpg?interpolation=lanczos-none&crop=w:w;*,*&crop=h:h;*,*&resize=225:*&output-format=jpg&output-quality=70")
                /** host image  */
                , "Kevin & Vicky"
                /** host name  */
                , Listing.ROOM_TYPE_ENTIRE_HOME
                /** room type  */
                , 5
                /** number of guests  */
                , 1
                /** number of bedrooms  */
                , 1
                /** number of beds  */
                , Uri.parse("https://a2.muscache.com/ac/users/4346177/profile_pic/1354866284/original.jpg?interpolation=lanczos-none&crop=w:w;*,*&crop=h:h;*,*&resize=225:*&output-format=jpg&output-quality=70")
                /** top review image  */
                , "Nina"
                /** top review name  */
                , GregorianCalendar(2015, 6, 1).getTimeInMillis()
                /** top review date  */
                , "We had the best time at the Steinbeck cottage. The place is really very very nice, " + "great attention to every detail, very clean and everything you could need " + "was provided. Thank you Kevin and Vicky, your place is amazing and we had " + "a great time!"
                /** top review message  */
                , 36.621019f
                /** lat  */
                , -121.921040f
                /** lng  */
                , "Eardley Avenue, Pacific Grove, CA 93950, United States"))
    }
}
