package com.vlsu.maps.ui.activity.main.fragment.map


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.TileOverlayOptions
import com.hannesdorfmann.mosby.mvp.viewstate.MvpViewStateFragment
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState
import com.vlsu.maps.R
import com.vlsu.maps.dagger.Dagger
import com.vlsu.maps.ui.activity.main.fragment.map.mvp.MapPresenter
import com.vlsu.maps.ui.activity.main.fragment.map.mvp.MapView
import kotlinx.android.synthetic.main.content_control.*
import kotlinx.android.synthetic.main.fragment_map.*


class MapFragment : MvpViewStateFragment<MapView, MapPresenter>(), MapView {

    private val component = Dagger.getComponent().mapComponent()
    private lateinit var googleMap: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        map.getMapAsync(onMapReadyCallback)

        layersButton.setOnClickListener { presenter.onLayersButtonClicked() }
        zoomInButton.setOnClickListener { presenter.onZoomInButtonClicked() }
        zoomOutButton.setOnClickListener { presenter.onZoomOutButtonClicked() }
        locationButton.setOnClickListener { presenter.onLocationButtonClicked() }

        return view
    }

    override fun zoomIn() {
        googleMap.animateCamera(CameraUpdateFactory.zoomIn())
    }

    override fun zoomOut() {
        googleMap.animateCamera(CameraUpdateFactory.zoomOut())
    }

    override fun showLayersDialog() {
        //TODO Layers dialog
    }

    override fun setOriginLocation(location: LatLng) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
    }

    override fun createViewState(): ViewState<MapView> {
        return component.mapViewState()
    }

    override fun onNewViewStateInstance() {

    }

    override fun createPresenter(): MapPresenter {
        return component.mapPresenter()
    }

    private val onMapReadyCallback = OnMapReadyCallback { googleMap ->
        this@MapFragment.googleMap = googleMap
            .apply {
                addTileOverlay(TileOverlayOptions().tileProvider(component.storageTileProvider()))
            }
    }
}
