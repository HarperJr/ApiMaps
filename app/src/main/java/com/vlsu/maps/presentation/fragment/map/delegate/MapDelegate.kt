package com.vlsu.maps.presentation.fragment.map.delegate

import android.annotation.SuppressLint
import android.content.Context
import com.mapbox.android.gestures.MoveGestureDetector
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponent
import com.mapbox.mapboxsdk.location.LocationComponentOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.vlsu.maps.presentation.fragment.map.interactor.Constants
import javax.inject.Inject

class MapDelegate @Inject constructor(
    private val context: Context
) : OnMapReadyCallback {

    var onMapReadyListener: (() -> Unit)? = null
    var onMapMoveListener: (() -> Unit)? = null

    private var map: MapboxMap? = null
    private var style: Style? = null

    private var locationComponent: LocationComponent? = null

    @SuppressLint("MissingPermission")
    override fun onMapReady(mapboxMap: MapboxMap) {
        this.map = mapboxMap
        mapboxMap.addOnMoveListener(onMoveListener)
        if (mapboxMap.style == null) {
            mapboxMap.setStyle(Style.MAPBOX_STREETS) { style ->
                this.style = style
                locationComponent = mapboxMap.locationComponent
                with(mapboxMap) {
                    locationComponent.activateLocationComponent(context, style)
                    locationComponent.isLocationComponentEnabled = true
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
        onMapReadyListener?.invoke()
    }

    fun zoomIn() {
        map?.animateCamera(CameraUpdateFactory.zoomIn())
    }

    fun zoomOut() {
        map?.animateCamera(CameraUpdateFactory.zoomOut())
    }

    fun zoom(zoom: Double) {
        map?.animateCamera(CameraUpdateFactory.zoomBy(zoom))
    }

    fun setMapCenter() {

    }

    fun addMarker() {

    }

    fun setOriginLocation(location: LatLng) {
        map?.moveCamera(CameraUpdateFactory.newLatLng(location))
    }

    fun destroy() {
        map?.removeOnMoveListener(onMoveListener)

        onMapReadyListener = null
        onMapMoveListener = null
        locationComponent = null
        map = null
    }

    private val onMoveListener = object : MapboxMap.OnMoveListener {
        override fun onMoveBegin(detector: MoveGestureDetector) {

        }

        override fun onMove(detector: MoveGestureDetector) {
            onMapMoveListener?.invoke()
        }

        override fun onMoveEnd(detector: MoveGestureDetector) {

        }
    }
}