package com.vlsu.maps.data.api.client

import com.vlsu.maps.data.api.interceptor.ApiKeyInterceptor
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