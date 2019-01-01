package com.vlsu.maps.api.interceptor

import com.vlsu.maps.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request()
            .url()
            .newBuilder()
            .addQueryParameter(API_KEY, BuildConfig.APPLICATION_ID)
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