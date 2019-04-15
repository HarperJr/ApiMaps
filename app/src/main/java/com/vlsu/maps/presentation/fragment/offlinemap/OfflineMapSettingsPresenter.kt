package com.vlsu.maps.presentation.fragment.offlinemap

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.vlsu.maps.domain.interactor.offlinemap.MapRegionLoader
import com.vlsu.maps.domain.interactor.region.RegionsProvider
import com.vlsu.maps.domain.rx.AppSchedulerProvider
import com.vlsu.maps.presentation.fragment.offlinemap.adapter.RegionItem
import io.reactivex.Completable
import io.reactivex.disposables.Disposables
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class OfflineMapSettingsPresenter @Inject constructor(
    private val regionLoader: MapRegionLoader,
    private val regionsProvider: RegionsProvider,
    private val router: Router
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

    fun onAcceptBtnClicked() {
        if (selectedRegion != null) {
            regionsDisposable = Completable
                .fromAction { regionLoader.loadRegion(selectedRegion!!) }
                .subscribeOn(AppSchedulerProvider.io())
                .observeOn(AppSchedulerProvider.ui())
                .subscribe({
                    router.exit()
                }, Timber::e)
        } else {
            Timber.e("region isn't selected yet")
        }
    }

    fun onRegionSelected(region: RegionItem) {
        selectedRegion = region.id
        ifViewAttached { view -> view.showDialog(region.name) }
    }
}