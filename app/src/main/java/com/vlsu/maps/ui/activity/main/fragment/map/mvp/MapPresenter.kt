package com.vlsu.maps.ui.activity.main.fragment.map.mvp

import com.google.android.gms.maps.model.LatLng
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.vlsu.maps.ui.handler.LocationUpdatesHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MapPresenter @Inject constructor(
    private val locationUpdatesHandler: LocationUpdatesHandler
) : MvpBasePresenter<MapView>() {

    private var locationUpdatesDisposable = Disposables.disposed()
    private var originFocused = false

    override fun attachView(view: MapView?) {
        locationUpdatesDisposable = locationUpdatesHandler.startUpdates()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { location ->
                LatLng(location.latitude, location.longitude)
            }
            .subscribe { location ->
                if (originFocused) {
                    view?.setOriginLocation(location)
                }
            }
    }

    override fun detachView(retainInstance: Boolean) {
        locationUpdatesHandler.stopUpdates()
        locationUpdatesDisposable.dispose()
    }

    fun onLayersButtonClicked() {
        view?.showLayersDialog()
    }

    fun onZoomInButtonClicked() {
        view?.zoomIn()
    }

    fun onZoomOutButtonClicked() {
        view?.zoomOut()
    }

    fun onLocationButtonClicked() {
        originFocused = true
    }
}