package com.vlsu.maps.dagger.module

import com.vlsu.maps.api.GoogleApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideGoogleApi(retrofit: Retrofit): GoogleApi {
        return retrofit.create(GoogleApi::class.java)
    }

    companion object {
        private const val BASE_URL = ""
    }
}