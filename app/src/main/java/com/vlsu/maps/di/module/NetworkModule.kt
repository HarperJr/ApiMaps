package com.vlsu.maps.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.vlsu.maps.BuildConfig
import com.vlsu.maps.data.api.client.ClientSettings
import com.vlsu.maps.data.api.net.TcpTunnel
import com.vlsu.maps.di.scope.AppScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @AppScope
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @AppScope
    fun provideRetrofit(clientSettings: ClientSettings, gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(clientSettings.getClient())
    }

    @Provides
    @AppScope
    fun provideTcpTunnel() = TcpTunnel(TUNNEL_HOST, TUNNEL_PORT)

    companion object {
        val TUNNEL_HOST = BuildConfig.TEST_TUNNEL_HOST
        const val TUNNEL_PORT = BuildConfig.TEST_TUNNEL_PORT
    }
}