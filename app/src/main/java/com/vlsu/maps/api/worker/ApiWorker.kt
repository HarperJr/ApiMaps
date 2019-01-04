package com.vlsu.maps.api.worker

import com.vlsu.maps.api.model.Point
import com.vlsu.maps.api.model.SnappedPoint
import io.reactivex.Single

interface ApiWorker {

    fun snapToRoads(path: List<Point>, interpolate: Boolean): Single<List<SnappedPoint>>

    fun nearestRoads(path: List<Point>): Single<List<SnappedPoint>>
}
