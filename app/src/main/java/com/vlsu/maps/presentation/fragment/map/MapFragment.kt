package com.vlsu.maps.presentation.fragment.map


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateFragment
import com.mapbox.mapboxsdk.geometry.LatLng
import com.vlsu.maps.R
import com.vlsu.maps.di.Dagger
import kotlinx.android.synthetic.main.fragment_map.*


class MapFragment : MvpViewStateFragment<MapView, MapPresenter, MapViewState>(),
    MapView {

    private val component = Dagger.appComponent.mapComponent()
    private val mapDelegate by lazy {
        component.mapDelegate()
    }
    private lateinit var mapView: com.mapbox.mapboxsdk.maps.MapView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        mapView = view.findViewById<com.mapbox.mapboxsdk.maps.MapView>(R.id.map)
            .apply {
                onCreate(savedInstanceState)
            }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layersButton.setOnClickListener { presenter.onLayersButtonClicked() }
        zoomInButton.setOnClickListener { presenter.onZoomInButtonClicked() }
        zoomOutButton.setOnClickListener { presenter.onZoomOutButtonClicked() }
        locationButton.setOnClickListener { presenter.onLocationButtonClicked() }

        mapView.getMapAsync(mapDelegate)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }

    override fun zoomIn() {
        mapDelegate.zoomIn()
    }

    override fun zoomOut() {
        mapDelegate.zoomOut()
    }

    override fun showLayersDialog() {
        //TODO Layers dialog
    }

    override fun setOriginLocation(location: LatLng) {

    }

    override fun createViewState(): MapViewState {
        return component.mapViewState()
    }

    override fun onNewViewStateInstance() {

    }

    override fun createPresenter(): MapPresenter {
        return component.mapPresenter()
    }

    companion object {
        fun newInstance() = MapFragment()

        const val REGION_SELECTOR_DIALOG_TAG = "REGION_SELECTOR_DIALOG_TAG"
    }
}
