package com.airbnb.net

import com.airbnb.Message

import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

interface AirbnbService {

    GET("/search_tab")
    public fun getSearchTabItems(): Observable<List<ListItem>>

    GET("/wish_list")
    public fun getWishList(): Observable<List<HeroItem>>

    GET("/messages")
    public fun getMessages(): Observable<List<Message>>

    GET("/listing/{id}")
    public fun getListing(Path("id") id: Long): Observable<Listing>

}
