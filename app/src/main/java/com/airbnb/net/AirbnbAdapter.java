package com.airbnb.net;

import java.util.List;

import retrofit.MockRestAdapter;
import retrofit.RestAdapter;
import rx.Observable;

public class AirbnbAdapter implements AirbnbService{
    private static final boolean USE_MOCK_ADAPTER = true;


    private static AirbnbAdapter sInstance;

    public static synchronized AirbnbAdapter getInstance() {
        if (sInstance == null) {
            sInstance = new AirbnbAdapter();
        }

        return sInstance;
    }

    private AirbnbService mService;

    private AirbnbAdapter() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://www.airbnb.com/api/v1")
                .build();

        if (USE_MOCK_ADAPTER) {
            MockRestAdapter mockAdapter = MockRestAdapter.from(adapter);
            mockAdapter.setDelay(250);
            mockAdapter.setVariancePercentage(50);
            mockAdapter.setErrorPercentage(0);
            mService = mockAdapter.create(AirbnbService.class, new MockAirbnbService());
        } else {
            mService = adapter.create(AirbnbService.class);
        }
    }

    @Override
    public Observable<List<ListItem>> getSearchTabItems() {
        return mService.getSearchTabItems();
    }

    @Override
    public Observable<List<HeroItem>> getWishList() {
        return mService.getWishList();
    }
}
