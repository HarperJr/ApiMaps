package com.vlsu.maps.api.worker

import com.vlsu.maps.api.ApiRoads
import com.vlsu.maps.api.converter.ResponseConverter
import com.vlsu.maps.api.model.Point
import com.vlsu.maps.api.model.SnappedPoint
import io.reactivex.Single
import javax.inject.Inject

class ApiWorkerImpl @Inject constructor(
    private val api: ApiRoads,
    private val responseConverter: ResponseConverter
) : ApiWorker {

    override fun snapToRoads(path: List<Point>, interpolate: Boolean): Single<List<SnappedPoint>> {
        return api.snapToRoads(path.joinToString("|") { point -> "${point.latitude},${point.longitude}" }, interpolate)
            .map { response ->
                responseConverter.convert(response)
            }
    }

    override fun nearestRoads(path: List<Point>): Single<List<SnappedPoint>> {
        return api.nearestRoads(path.joinToString("|") { point -> "${point.latitude},${point.longitude}" })
            .map { response ->
                responseConverter.convert(response)
            }
    }
}