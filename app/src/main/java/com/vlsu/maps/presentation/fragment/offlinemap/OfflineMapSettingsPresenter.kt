package com.vlsu.maps.presentation.fragment.offlinemap

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.vlsu.maps.domain.interactor.region.RegionsProvider
import com.vlsu.maps.domain.rx.AppSchedulerProvider
import com.vlsu.maps.domain.rx.RxBus
import io.reactivex.disposables.Disposables
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class OfflineMapSettingsPresenter @Inject constructor(
    private val router: Router,
    private val rxBus: RxBus,
    private val regionsProvider: RegionsProvider
) : MvpBasePresenter<OfflineMapSettingsView>() {

    private var regionsDisposable = Disposables.disposed()
    private var selectedRegion: Long? = null

    override fun attachView(view: OfflineMapSettingsView) {
        super.attachView(view)
        regionsDisposable = regionsProvider
            .regions()
            .subscribeOn(AppSchedulerProvider.io())
            .observeOn(AppSchedulerProvider.ui())
            .subscribe({ regions ->
                view.setRegions(regions)
            }, Timber::e)
    }

    override fun detachView() {
        regionsDisposable.dispose()
        super.detachView()
    }

    fun onConfirmBtnClicked() {
        if (selectedRegion != null) {

        }
    }

    fun onRegionSelected(regionId: Long) {
        selectedRegion = regionId
    }

    fun onRecycleBtnClicked() {

    }
}