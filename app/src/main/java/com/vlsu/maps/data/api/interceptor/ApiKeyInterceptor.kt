package com.vlsu.maps.data.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiKeyInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request()
            .url()
            .newBuilder()
            .build()
        val requestBuilder = chain.request()
            .newBuilder()
            .url(url)
        return chain.proceed(requestBuilder.build())
    }

    companion object {
        private const val API_KEY = "key"
    }
}