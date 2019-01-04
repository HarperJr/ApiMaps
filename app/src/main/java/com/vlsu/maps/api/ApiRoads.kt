package com.vlsu.maps.api

import com.vlsu.maps.api.model.SnappedPoint
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRoads {

    @GET("snapToRoads")
    fun snapToRoads(
        @Query("path") path: String,
        @Query("interpolate") interpolate: Boolean
    ): Single<Response<List<SnappedPoint>>>

    @GET("nearestRoads")
    fun nearestRoads(
        @Query("points") points: String
    ): Single<Response<List<SnappedPoint>>>

}