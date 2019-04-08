package com.vlsu.maps.presentation.fragment.map


import android.Manifest
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.CoordinatorLayout
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateFragment
import com.mapbox.mapboxsdk.geometry.LatLng
import com.tbruyelle.rxpermissions2.RxPermissions
import com.vlsu.maps.R
import com.vlsu.maps.di.Dagger
import com.vlsu.maps.navigation.map.Navigator
import com.vlsu.maps.presentation.OnBackPressable
import io.reactivex.disposables.Disposables
import kotlinx.android.synthetic.main.fragment_map.*
import timber.log.Timber


class MapFragment : MvpViewStateFragment<MapView, MapPresenter, MapViewState>(),
    MapView, BottomNavigationView.OnNavigationItemSelectedListener, OnBackPressable {

    private val rxPermissions by lazy { RxPermissions(this) }
    private var permissionsDisposable = Disposables.disposed()

    private val component = Dagger.appComponent.mapComponent()
    private val mapDelegate by lazy {
        component.mapDelegate()
    }
    private val navigator by lazy {
        Navigator(childFragmentManager, R.id.map_bottom_container)
    }
    private lateinit var mapView: com.mapbox.mapboxsdk.maps.MapView

    private val fragmentRouter = component.fragmentRouter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
        requestPermissionsRx()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        mapView = view.findViewById<com.mapbox.mapboxsdk.maps.MapView>(R.id.map)
            .apply { onCreate(savedInstanceState) }
        mapView.getMapAsync(mapDelegate)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        map_layers_btn.setOnClickListener { presenter.onLayersButtonClicked() }
        map_zoom_in_btn.setOnClickListener { presenter.onZoomInButtonClicked() }
        map_zoom_out_btn.setOnClickListener { presenter.onZoomOutButtonClicked() }
        map_origin_btn.setOnClickListener { presenter.onLocationButtonClicked() }

        map_navbar.setOnNavigationItemSelectedListener(this)

        ((map_bottom_container.layoutParams as CoordinatorLayout.LayoutParams)
            .behavior as BottomSheetBehavior).setBottomSheetCallback(bottomSheetStateCallback)

        mapDelegate.onMapReadyListener = presenter::onMapReady
        mapDelegate.onMapMoveListener = presenter::onMapMoved
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_selection_notifications -> {
                presenter.navigateToNotifications()
                true
            }
            R.id.menu_selection_settings -> {
                presenter.navigateToSettings()
                true
            }
            R.id.menu_selection_routing -> {
                presenter.navigateToRoute()
                true
            }
            else -> false
        }
    }

    override fun setOriginBtnActive(active: Boolean) {
        map_origin_btn.isActivated = active
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
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
        mapDelegate.destroy()
        permissionsDisposable.dispose()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
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

    override fun setBottomSheetExpanded(expanded: Boolean) {
        ((map_bottom_container.layoutParams as CoordinatorLayout.LayoutParams)
            .behavior as BottomSheetBehavior).state = if (expanded) BottomSheetBehavior.STATE_EXPANDED
        else BottomSheetBehavior.STATE_HIDDEN
    }

    override fun setProgress(progress: Int) {
        map_progress_bar.progress = progress
    }

    override fun setProgressVisible(visivle: Boolean) {
        map_progress_bar.visibility = if (visivle) View.VISIBLE else View.INVISIBLE
    }

    private fun requestPermissionsRx() {
        permissionsDisposable = rxPermissions
            .request(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            .subscribe { granted ->
                if (!granted) {
                    Timber.d("Permissions not granted")
                }
            }
    }

    private val bottomSheetStateCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onSlide(view: View, slideOffset: Float) {

        }

        override fun onStateChanged(view: View, state: Int) {
            if (state == BottomSheetBehavior.STATE_HIDDEN) presenter.onBottomSheetCollapsed()
        }
    }

    companion object {
        fun newInstance() = MapFragment()
    }
}
