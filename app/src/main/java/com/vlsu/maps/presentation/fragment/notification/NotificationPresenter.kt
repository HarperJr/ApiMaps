package com.vlsu.maps.presentation.fragment.notification

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.vlsu.maps.domain.interactor.notification.NotificationsProvider
import com.vlsu.maps.domain.rx.AppSchedulerProvider
import com.vlsu.maps.navigation.map.MapScreenRouter
import com.vlsu.maps.presentation.fragment.notifying.NotifyingScreen
import io.reactivex.disposables.Disposables
import timber.log.Timber
import javax.inject.Inject

class NotificationPresenter @Inject constructor(
    private val notificationsProvider: NotificationsProvider,
    private val mapScreenRouter: MapScreenRouter
) : MvpBasePresenter<NotificationView>() {

    private var notificationsDisposable = Disposables.disposed()

    override fun attachView(view: NotificationView) {
        super.attachView(view)
        Timber.d("attachView")
        notificationsDisposable = notificationsProvider
            .notifications()
            .subscribeOn(AppSchedulerProvider.io())
            .observeOn(AppSchedulerProvider.ui())
            .subscribe(view::setNotifications, Timber::e)
    }

    override fun detachView() {
        notificationsDisposable.dispose()
        super.detachView()
    }

    fun onAddBtnClicked() {
        mapScreenRouter.replace(NotifyingScreen())
    }
}
