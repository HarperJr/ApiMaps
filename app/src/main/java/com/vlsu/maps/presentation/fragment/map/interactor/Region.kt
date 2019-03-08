package com.vlsu.maps.presentation.fragment.map.interactor

import com.mapbox.mapboxsdk.geometry.LatLngBounds

enum class Region(val code: String, val latlngBounds: LatLngBounds) {

    VLADIMIR("VLADIMIR", LatLngBounds.from(
        56.202674, 40.523022, 56.111151, 40.313821
    ))
}