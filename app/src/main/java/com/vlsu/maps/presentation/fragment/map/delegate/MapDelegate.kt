package com.vlsu.maps.presentation.fragment.map.delegate

import android.content.Context
import android.support.annotation.DrawableRes
import com.mapbox.android.gestures.MoveGestureDetector
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponent
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.LocationComponentOptions
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.vlsu.maps.domain.interactor.offlinemap.Constants
import javax.inject.Inject

class MapDelegate @Inject constructor(
    private val context: Context
) {

    var onMapReadyListener: (() -> Unit)? = null
    var onMapMoveListener: (() -> Unit)? = null

    private var mapView: MapView? = null
    private var map: MapboxMap? = null
    private var style: Style? = null

    private var locationComponent: LocationComponent? = null

    private var symbolManager: SymbolManager? = null

    fun onMapReady(mapView: MapView, mapboxMap: MapboxMap) {
        this.mapView = mapView
        this.map = mapboxMap
        mapboxMap.addOnMoveListener(onMoveListener)
        if (mapboxMap.style == null) {
            mapboxMap.setStyle(Style.MAPBOX_STREETS) { style ->
                this.style = style

                with(mapboxMap) {
                    with(locationComponent) {
                        val locationComponentOptions = LocationComponentOptions.builder(context)
                            .elevation(5.0f)
                            .accuracyAlpha(0.6f)
                            .build()
                        val options = LocationComponentActivationOptions
                            .builder(context, style)
                            .locationComponentOptions(locationComponentOptions)
                            .build()
                        activateLocationComponent(options)
                        isLocationComponentEnabled = true
                        renderMode = RenderMode.GPS
                    }
                    with(uiSettings) {
                        isCompassEnabled = true
                        isAttributionEnabled = false
                        isLogoEnabled = false
                    }
                    setMinZoomPreference(Constants.MIN_ZOOM)
                    setMaxZoomPreference(Constants.MAX_ZOOM)
                }
                this@MapDelegate.symbolManager = SymbolManager(mapView, mapboxMap, style)
            }
            this@MapDelegate.locationComponent = mapboxMap.locationComponent
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

    fun setOriginMarker(title: String, @DrawableRes icon: Int? = null) {

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
        symbolManager = null
        mapView = null
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