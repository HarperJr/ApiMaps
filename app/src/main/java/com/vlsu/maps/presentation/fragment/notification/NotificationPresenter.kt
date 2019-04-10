package com.vlsu.maps.presentation.fragment.notification

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.vlsu.maps.domain.interactor.notification.NotificationsProvider
import com.vlsu.maps.domain.rx.AppSchedulerProvider
import io.reactivex.disposables.Disposables
import timber.log.Timber
import javax.inject.Inject

class NotificationPresenter @Inject constructor(
    private val notificationsProvider: NotificationsProvider
) : MvpBasePresenter<NotificationView>() {

    private var notificationsDisposable = Disposables.disposed()

    override fun attachView(view: NotificationView) {
        super.attachView(view)
        notificationsDisposable = notificationsProvider
            .notifications()
            .subscribeOn(AppSchedulerProvider.ui())
            .observeOn(AppSchedulerProvider.ui())
            .subscribe(view::setNotifications, Timber::e)
    }

    override fun detachView() {
        notificationsDisposable.dispose()
        super.detachView()
    }
}
