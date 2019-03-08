package com.vlsu.maps.presentation.fragment.map

import android.content.SharedPreferences
import com.google.android.gms.maps.model.LatLng
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.vlsu.maps.domain.interactor.location.LocationUpdatesInteractor
import com.vlsu.maps.domain.rx.AppSchedulerProvider
import com.vlsu.maps.domain.rx.RegionChangedEvent
import com.vlsu.maps.domain.rx.RxBus
import com.vlsu.maps.presentation.fragment.map.interactor.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MapPresenter @Inject constructor(
    private val locationUpdatesInteractor: LocationUpdatesInteractor,
    private val sharedPreferences: SharedPreferences,
    private val rxBus: RxBus
) : MvpBasePresenter<MapView>() {

    private var locationUpdatesDisposable = Disposables.disposed()
    private var rxBusDisposable = Disposables.disposed()
    private var originFocused = false

    override fun attachView(view: MapView) {
        if (!sharedPreferences.getBoolean(Constants.PREFS_REGION_LOADED, false)) {
            ifViewAttached { it.showRegionSelectorDialog() }
        }

        locationUpdatesDisposable = locationUpdatesInteractor.updates()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { location ->
                LatLng(location.latitude, location.longitude)
            }
            .subscribe { location ->
                if (originFocused) {

                }
            }
        rxBusDisposable = rxBus.event()
            .observeOn(AppSchedulerProvider.ui())
            .subscribe { event ->
                when (event) {
                    is RegionChangedEvent -> ifViewAttached { it.hideRegionSelectorDialog() }
                }
            }
    }

    override fun detachView() {
        locationUpdatesInteractor.stopUpdates()
        locationUpdatesDisposable.dispose()
        rxBusDisposable.dispose()
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
        originFocused = true
    }
}