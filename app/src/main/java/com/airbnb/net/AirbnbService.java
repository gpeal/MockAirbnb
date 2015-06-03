package com.airbnb.net;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

interface AirbnbService {

    @GET("/search_tab")
    Observable<List<ListItem>> getSearchTabItems();

}
