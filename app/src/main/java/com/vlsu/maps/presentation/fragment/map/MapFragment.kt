package com.vlsu.maps.presentation.fragment.map


import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateFragment
import com.mapbox.mapboxsdk.geometry.LatLng
import com.vlsu.maps.R
import com.vlsu.maps.di.Dagger
import com.vlsu.maps.navigation.MapNavigator
import com.vlsu.maps.presentation.OnBackPressable
import kotlinx.android.synthetic.main.fragment_map.*


class MapFragment : MvpViewStateFragment<MapView, MapPresenter, MapViewState>(),
    MapView, BottomNavigationView.OnNavigationItemSelectedListener, OnBackPressable {

    private val component = Dagger.appComponent.mapComponent()
    private val mapDelegate by lazy {
        component.mapDelegate()
    }
    private val navigator by lazy {
        MapNavigator(childFragmentManager, R.id.bottomContainer)
    }
    private lateinit var mapView: com.mapbox.mapboxsdk.maps.MapView

    private val fragmentRouter = component.fragmentRouter()

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

        bottomNavBar.setOnNavigationItemSelectedListener(this)

        mapView.getMapAsync(mapDelegate)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuSelectionNotifications -> {
                presenter.navigateToNotifications()
                true
            }
            R.id.menuSelectionSettings -> {
                presenter.navigateToSettings()
                true
            }
            R.id.menuSelectionRoute -> {
                presenter.navigateToRoute()
                true
            }
            else -> false
        }
    }

    override fun onBackPressed(): Boolean {
        return presenter.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        fragmentRouter.setNavigator(navigator)
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        fragmentRouter.removeNavigator()
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
        mapDelegate.setOriginLocation(location)
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
