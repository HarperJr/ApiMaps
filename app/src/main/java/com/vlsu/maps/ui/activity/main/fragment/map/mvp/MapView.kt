package com.vlsu.maps.ui.activity.main.fragment.map.mvp

import com.google.android.gms.maps.model.LatLng
import com.hannesdorfmann.mosby.mvp.MvpView
import org.osmdroid.util.GeoPoint

interface MapView : MvpView {

    fun zoomIn()

    fun zoomOut()

    fun showLayersDialog()

    fun setOriginLocation(location: GeoPoint)
}