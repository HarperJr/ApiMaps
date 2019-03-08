package com.vlsu.maps.presentation.fragment.map

import com.google.android.gms.maps.model.LatLng
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.vlsu.maps.domain.interactor.LocationUpdatesInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MapPresenter @Inject constructor(
    private val locationUpdatesInteractor: LocationUpdatesInteractor
) : MvpBasePresenter<MapView>() {

    private var locationUpdatesDisposable = Disposables.disposed()
    private var originFocused = false

    override fun attachView(view: MapView) {
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
    }

    override fun detachView() {
        locationUpdatesInteractor.stopUpdates()
        locationUpdatesDisposable.dispose()
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