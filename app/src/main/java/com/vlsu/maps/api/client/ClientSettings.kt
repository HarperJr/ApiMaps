package com.vlsu.maps.api.client

import com.vlsu.maps.api.interceptor.ApiKeyInterceptor
import okhttp3.OkHttpClient
import javax.inject.Inject

class ClientSettings @Inject constructor(
    private val apiKeyInterceptor: ApiKeyInterceptor
) {

    fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()
    }
}