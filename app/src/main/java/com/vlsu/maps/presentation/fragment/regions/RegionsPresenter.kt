package com.vlsu.maps.presentation.fragment.regions

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.vlsu.maps.domain.interactor.region.RegionsProvider
import com.vlsu.maps.domain.rx.AppSchedulerProvider
import io.reactivex.disposables.Disposables
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class RegionsPresenter @Inject constructor(
    private val router: Router,
    private val regionsProvider: RegionsProvider
) : MvpBasePresenter<RegionsView>() {

    private var regionsDisposable = Disposables.disposed()

    override fun attachView(view: RegionsView) {
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
}