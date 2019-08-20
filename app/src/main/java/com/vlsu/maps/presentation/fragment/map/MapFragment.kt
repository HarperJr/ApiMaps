package com.vlsu.maps.presentation.fragment.map


import android.Manifest
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.CoordinatorLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateFragment
import com.mapbox.mapboxsdk.geometry.LatLng
import com.tbruyelle.rxpermissions2.RxPermissions
import com.vlsu.maps.R
import com.vlsu.maps.di.Dagger
import com.vlsu.maps.domain.model.NotificationType
import com.vlsu.maps.extensions.OnBackPressable
import com.vlsu.maps.navigation.map.Navigator
import io.reactivex.disposables.Disposables
import kotlinx.android.synthetic.main.fragment_map.*
import timber.log.Timber


class MapFragment : MvpViewStateFragment<MapView, MapPresenter, MapViewState>(),
    MapView, OnBackPressable {

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
    private val notificationBarController = object {
        lateinit var reveal: () -> Unit
        lateinit var hide: () -> Unit
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        mapView = view.findViewById<com.mapbox.mapboxsdk.maps.MapView>(R.id.map)
            .apply { onCreate(savedInstanceState) }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        map_zoom_in_btn.setOnClickListener { presenter.onZoomInButtonClicked() }
        map_zoom_out_btn.setOnClickListener { presenter.onZoomOutButtonClicked() }
        map_origin_btn.setOnClickListener { presenter.onLocationButtonClicked() }

        ((map_bottom_container.layoutParams as CoordinatorLayout.LayoutParams)
            .behavior as BottomSheetBehavior).setBottomSheetCallback(bottomSheetStateCallback)

        nav_notifications.setOnClickListener { presenter.navigateToNotifications() }
        nav_routing.setOnClickListener { presenter.navigateToRouting() }
        nav_settings.setOnClickListener { presenter.navigateToSettings() }

        mapDelegate.onMapReadyListener = presenter::onMapReady
        mapDelegate.onMapMoveListener = presenter::onMapMoved

        notificationBarController.reveal = {
            ObjectAnimator.ofFloat(notification_bar, "translationY", 0f).apply {
                duration = NOTIFICATION_BAR_REVEAL_DURATION
                start()
            }
        }
        notificationBarController.hide = {
            val height = notification_bar.height.toFloat()
            ObjectAnimator.ofFloat(notification_bar, "translationY", -height).apply {
                duration = NOTIFICATION_BAR_REVEAL_DURATION
                start()
            }
        }
    }

    override fun setNotificationBarRevealed(revealed: Boolean) {
        (if (revealed) notificationBarController.reveal else notificationBarController.hide).invoke()
    }

    override fun setOriginBtnActive(active: Boolean) {
        map_origin_btn.isActivated = active
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
        requestPermissionsRx()
    }

    override fun onStop() {
        mapView.onStop()
        super.onStop()
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun zoomIn() {
        mapDelegate.zoomIn()
    }

    override fun zoomOut() {
        mapDelegate.zoomOut()
    }

    override fun setOriginLocation(location: LatLng) {
        mapDelegate.setOriginLocation(location)
    }

    override fun setNotification(notification: String, type: NotificationType) {
        when (type) {
            NotificationType.AMBULANCE -> mapDelegate.setOriginMarker(notification, R.drawable.ic_ambulance)
            NotificationType.COLLAPSE -> mapDelegate.setOriginMarker(notification, R.drawable.ic_collapse)
            NotificationType.SUPPORT_REQUEST -> mapDelegate.setOriginMarker(notification, R.drawable.ic_support)
            NotificationType.MESSAGE -> mapDelegate.setOriginMarker(notification)
            else -> Timber.e("Unhandled notification type")
        }
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

    override fun setProgressVisible(visible: Boolean) {
        map_progress_bar.visibility = if (visible) View.VISIBLE else View.INVISIBLE
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
                } else {
                    mapView.getMapAsync { mapboxMap ->
                        mapDelegate.onMapReady(mapView, mapboxMap)
                    }
                }
            }
    }

    private val bottomSheetStateCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onSlide(view: View, slideOffset: Float) {

        }

        override fun onStateChanged(view: View, state: Int) {
            if (state == BottomSheetBehavior.STATE_HIDDEN) presenter.onBottomSheetHidden()
        }
    }

    companion object {
        private const val NOTIFICATION_BAR_REVEAL_DURATION = 500L
        fun newInstance() = MapFragment()
    }
}
