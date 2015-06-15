package com.airbnb.net;

import com.airbnb.Message;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

interface AirbnbService {

    @GET("/search_tab")
    Observable<List<ListItem>> getSearchTabItems();

    @GET("/wish_list")
    Observable<List<HeroItem>> getWishList();

    @GET("/messages")
    Observable<List<Message>> getMessages();

}
