package com.vlsu.maps.presentation.fragment.map.delegate

import android.content.Context
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.vlsu.maps.presentation.fragment.map.interactor.Constants
import javax.inject.Inject

class MapDelegate @Inject constructor(
    private val context: Context
) : OnMapReadyCallback {

    private lateinit var map: MapboxMap
    private lateinit var style: Style

    override fun onMapReady(mapboxMap: MapboxMap) {
        this.map = mapboxMap
        if (mapboxMap.style == null) {
            mapboxMap.setStyle(Style.MAPBOX_STREETS) { style ->
                this.style = style
                with(mapboxMap) {
                    with(uiSettings) {
                        isCompassEnabled = true
                        isAttributionEnabled = false
                        isLogoEnabled = false
                    }
                    setMinZoomPreference(Constants.MIN_ZOOM)
                    setMaxZoomPreference(Constants.MAX_ZOOM)
                }
            }
        }
    }

    fun zoomIn() {
        map.animateCamera(CameraUpdateFactory.zoomIn())
    }

    fun zoomOut() {
        map.animateCamera(CameraUpdateFactory.zoomOut())
    }

    fun zoom(zoom: Double) {
        map.animateCamera(CameraUpdateFactory.zoomBy(zoom))
    }

    fun setMapCenter() {

    }

    fun addMarker() {

    }

    fun setOriginLocation(location: LatLng) {
        map.moveCamera(CameraUpdateFactory.newLatLng(location))
    }
}