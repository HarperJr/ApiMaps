package com.vlsu.maps.api.converter

import retrofit2.Response

class ResponseConverter {

    fun <T> convert(response: Response<T>): T? {
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }
}