package com.vlsu.maps.presentation.dialog

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.vlsu.maps.domain.interactor.region.RegionInteractor
import com.vlsu.maps.domain.model.Region
import com.vlsu.maps.domain.rx.AppSchedulerProvider
import com.vlsu.maps.domain.rx.RegionChangedEvent
import com.vlsu.maps.domain.rx.RxBus
import com.vlsu.maps.presentation.fragment.notification.adapter.RegionItem
import io.reactivex.disposables.Disposables
import timber.log.Timber
import javax.inject.Inject

class RegionSelectorPresenter @Inject constructor(
    private val rxBus: RxBus,
    private val regionInteractor: RegionInteractor
) : MvpBasePresenter<RegionSelectorView>() {

    private var regionLoaderDisposable = Disposables.disposed()
    private var selectedRegionId: Long? = null

    override fun attachView(view: RegionSelectorView) {
        super.attachView(view)
        regionLoaderDisposable = regionInteractor.regions()
            .subscribeOn(AppSchedulerProvider.bg())
            .observeOn(AppSchedulerProvider.ui())
            .map { mapToRegionItems(it) }
            .subscribe({ regions ->
                ifViewAttached { it.setRegions(regions) }
            }, { ex -> Timber.e(ex) })
    }

    override fun detachView() {
        regionLoaderDisposable.dispose()
        super.detachView()
    }

    fun onApplyClicked() {
        if (selectedRegionId != null) {
            rxBus.onEvent(RegionChangedEvent(selectedRegionId!!))
        }
    }

    fun onRegionItemClicked(id: Long) {
        selectedRegionId = id
    }

    private fun mapToRegionItems(regions: List<Region>) = regions.map {
        RegionItem(it.id, it.code, it.name)
    }
}