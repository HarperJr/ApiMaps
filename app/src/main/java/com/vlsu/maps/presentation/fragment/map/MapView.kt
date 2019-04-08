package com.vlsu.maps.presentation.fragment.map

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.mapbox.mapboxsdk.geometry.LatLng

interface MapView : MvpView {

    fun setOriginBtnActive(active: Boolean)

    fun zoomIn()

    fun zoomOut()

    fun showLayersDialog()

    fun setOriginLocation(location: LatLng)

    fun setBottomSheetExpanded(expanded: Boolean)
}