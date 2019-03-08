package com.vlsu.maps.domain.model

class Region(
    var id: Long,
    var code: String,
    var name: String,
    var order: Long,
    var north: Double,
    var south: Double,
    var east: Double,
    var west: Double
)