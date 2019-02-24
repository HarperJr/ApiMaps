package com.vlsu.maps.ui.activity.main.fragment.map.mvp

import com.hannesdorfmann.mosby.mvp.MvpView
import com.mapbox.mapboxsdk.geometry.LatLng

interface MapView : MvpView {

    fun zoomIn()

    fun zoomOut()

    fun showLayersDialog()

    fun setOriginLocation(location: LatLng)
}