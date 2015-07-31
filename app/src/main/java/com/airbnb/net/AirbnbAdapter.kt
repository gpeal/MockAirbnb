package com.airbnb.net

import com.airbnb.Message

import retrofit.MockRestAdapter
import retrofit.RestAdapter
import retrofit.http.Path
import rx.Observable
import kotlin.properties.Delegates

public class AirbnbAdapter private constructor() : AirbnbService {
    companion object {
        private val USE_MOCK_ADAPTER = true


        private val sInstance: AirbnbAdapter by Delegates.lazy {
            AirbnbAdapter()
        }

        synchronized public fun getInstance(): AirbnbAdapter {
            return sInstance
        }
    }

    private var mService: AirbnbService

    init {
        val adapter = RestAdapter.Builder()
                .setEndpoint("http://www.airbnb.com/api/v1")
                .build()

        if (USE_MOCK_ADAPTER) {
            val mockAdapter = MockRestAdapter.from(adapter)
            mockAdapter.setDelay(250)
            mockAdapter.setVariancePercentage(50)
            mockAdapter.setErrorPercentage(0)
            mService = mockAdapter.create(javaClass<AirbnbService>(), MockAirbnbService())
        } else {
            mService = adapter.create(javaClass<AirbnbService>())
        }
    }

    override fun getSearchTabItems(): Observable<MutableList<ListItem>> {
        return mService.getSearchTabItems()
    }

    override fun getWishList(): Observable<MutableList<HeroItem>> {
        return mService.getWishList()
    }

    override fun getMessages(): Observable<MutableList<Message>> {
        return mService.getMessages()
    }

    override fun getListing(Path("id") id: Long): Observable<Listing> {
        return mService.getListing(id)
    }
}
