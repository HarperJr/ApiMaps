package com.vlsu.maps.dagger.module

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.vlsu.maps.BuildConfig
import com.vlsu.maps.api.GoogleApi
import com.vlsu.maps.api.client.ClientSettings
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(clientSettings: ClientSettings): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(clientSettings.getClient())
            .build()
    }

    @Provides
    fun provideGoogleApi(retrofit: Retrofit): GoogleApi {
        return retrofit
            .create(GoogleApi::class.java)
    }

    companion object {
        private const val BASE_URL = BuildConfig.GOOGLE_API_BASE_URL
    }
}