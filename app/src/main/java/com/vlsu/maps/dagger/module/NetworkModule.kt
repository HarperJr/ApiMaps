package com.vlsu.maps.dagger.module

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.vlsu.maps.BuildConfig
import com.vlsu.maps.api.ApiMaps
import com.vlsu.maps.api.client.ClientSettings
import com.vlsu.maps.api.ApiRoads
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(clientSettings: ClientSettings): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(clientSettings.getClient())
    }

    @Provides
    fun provideGoogleMapsApi(retrofit: Retrofit.Builder): ApiMaps {
        return retrofit
            .baseUrl(MAPS_BASE_URL)
            .build()
            .create(ApiMaps::class.java)
    }

    @Provides
    fun provideGoogleRoadsApi(retrofit: Retrofit.Builder): ApiRoads {
        return retrofit
            .baseUrl(ROADS_BASE_URL)
            .build()
            .create(ApiRoads::class.java)
    }

    companion object {
        private const val ROADS_BASE_URL = BuildConfig.GOOGLE_ROADS_API_BASE_URL
        private const val MAPS_BASE_URL = BuildConfig.GOOGLE_MAPS_API_BASE_URL
    }
}