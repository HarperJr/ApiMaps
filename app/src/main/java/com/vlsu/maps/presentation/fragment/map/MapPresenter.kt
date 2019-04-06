package com.vlsu.maps.presentation.fragment.map

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.mapbox.mapboxsdk.geometry.LatLng
import com.vlsu.maps.domain.interactor.location.LocationUpdatesProvider
import com.vlsu.maps.domain.rx.AppSchedulerProvider
import com.vlsu.maps.domain.rx.RegionChangedEvent
import com.vlsu.maps.domain.rx.RxBus
import com.vlsu.maps.navigation.FragmentRouter
import com.vlsu.maps.presentation.fragment.notification.NotificationScreen
import com.vlsu.maps.presentation.fragment.routing.RoutingScreen
import com.vlsu.maps.presentation.fragment.settings.SettingsScreen
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MapPresenter @Inject constructor(
    private val locationUpdatesProvider: LocationUpdatesProvider,
    private val router: FragmentRouter,
    private val rxBus: RxBus
) : MvpBasePresenter<MapView>() {

    private var locationUpdatesDisposable = Disposables.disposed()
    private var rxBusDisposable = Disposables.disposed()
    private var downloadStatusDisposable = Disposables.disposed()
    private var originFocused = false

    override fun attachView(view: MapView) {
        locationUpdatesProvider.startUpdates()

        locationUpdatesDisposable = locationUpdatesProvider.updates()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { location ->
                LatLng(location.latitude, location.longitude)
            }
            .subscribe { location ->
                if (originFocused) {
                    view.setOriginLocation(location)
                }
            }
        rxBusDisposable = rxBus.event()
            .observeOn(AppSchedulerProvider.ui())
            .subscribe { event ->
                when (event) {
                    is RegionChangedEvent -> downloadRegion(event.regionId)
                }
            }
    }

    override fun detachView() {
        locationUpdatesProvider.stopUpdates()

        locationUpdatesDisposable.dispose()
        rxBusDisposable.dispose()
        downloadStatusDisposable.dispose()

        super.detachView()
    }

    fun onLayersButtonClicked() {
        ifViewAttached { it.showLayersDialog() }
    }

    fun onZoomInButtonClicked() {
        ifViewAttached { it.zoomIn() }
    }

    fun onZoomOutButtonClicked() {
        ifViewAttached { it.zoomOut() }
    }

    fun onLocationButtonClicked() {
        originFocused = !originFocused
    }

    private fun downloadRegion(regionId: Long) {

    }

    fun navigateToNotifications() {
        router.replace(NotificationScreen())
    }

    fun navigateToSettings() {
        router.replace(SettingsScreen())
    }

    fun navigateToRoute() {
        router.replace(RoutingScreen())
    }

    fun onBackPressed(): Boolean {
        return router.back()
    }
}