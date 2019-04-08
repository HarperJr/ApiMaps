package com.vlsu.maps.presentation.fragment.map

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.mapbox.mapboxsdk.geometry.LatLng
import com.vlsu.maps.di.scope.AppScope
import com.vlsu.maps.domain.interactor.location.LocationUpdatesProvider
import com.vlsu.maps.domain.rx.AppSchedulerProvider
import com.vlsu.maps.domain.rx.RegionChangedEvent
import com.vlsu.maps.domain.rx.RxBus
import com.vlsu.maps.navigation.map.MapScreenRouter
import com.vlsu.maps.presentation.fragment.map.interactor.MapRegionLoader
import com.vlsu.maps.presentation.fragment.notification.NotificationScreen
import com.vlsu.maps.presentation.fragment.routing.RoutingScreen
import com.vlsu.maps.presentation.fragment.settings.SettingsScreen
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@AppScope
class MapPresenter @Inject constructor(
    private val locationUpdatesProvider: LocationUpdatesProvider,
    private val mapScreenRouter: MapScreenRouter,
    private val regionLoader: MapRegionLoader,
    private val rxBus: RxBus
) : MvpBasePresenter<MapView>() {

    private var locationUpdatesDisposable = Disposables.disposed()
    private var rxBusDisposable = Disposables.disposed()
    private var downloadStatusDisposable = Disposables.disposed()

    override fun attachView(view: MapView) {
        super.attachView(view)

        rxBusDisposable = rxBus.event()
            .subscribe {
                if (it is RegionChangedEvent) {
                    downloadRegion(it.regionId)
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

    fun onMapReady() {
        observeLocationUpdates()
    }

    fun onMapMoved() {
        disposeLocationUpdates()
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
        observeLocationUpdates()
    }

    fun navigateToNotifications() {
        mapScreenRouter.replace(NotificationScreen())
        ifViewAttached { it.setBottomSheetExpanded(true) }
    }

    fun navigateToSettings() {
        mapScreenRouter.replace(SettingsScreen())
        ifViewAttached { it.setBottomSheetExpanded(true) }
    }

    fun navigateToRoute() {
        mapScreenRouter.replace(RoutingScreen())
        ifViewAttached { it.setBottomSheetExpanded(true) }
    }

    fun onBackPressed() {
        mapScreenRouter.back()
    }

    fun onBottomSheetCollapsed() {
        mapScreenRouter.back()
    }

    private fun downloadRegion(regionId: Long) {
        downloadStatusDisposable = Completable
            .fromAction { regionLoader.loadRegion(regionId) }
            .andThen(regionLoader.downloadStatus())
            .subscribeOn(AppSchedulerProvider.io())
            .observeOn(AppSchedulerProvider.ui())
            .doOnSubscribe { ifViewAttached { it.setProgressVisible(true) } }
            .doOnComplete { ifViewAttached { it.setProgressVisible(true) } }
            .subscribe({ progress -> ifViewAttached { it.setProgress(progress) } }, Timber::e)
    }

    private fun observeLocationUpdates() {
        locationUpdatesProvider.startUpdates()
        locationUpdatesDisposable = locationUpdatesProvider.updates()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { location ->
                LatLng(location.latitude, location.longitude)
            }
            .subscribe { location -> ifViewAttached { it.setOriginLocation(location) } }
        ifViewAttached { it.setOriginBtnActive(true) }
    }

    private fun disposeLocationUpdates() {
        locationUpdatesProvider.stopUpdates()
        locationUpdatesDisposable.dispose()

        ifViewAttached { it.setOriginBtnActive(false) }
    }
}