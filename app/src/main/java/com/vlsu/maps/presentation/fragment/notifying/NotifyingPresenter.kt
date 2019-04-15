package com.vlsu.maps.presentation.fragment.notifying

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.vlsu.maps.domain.interactor.notification.NotificationsManager
import com.vlsu.maps.domain.model.NotificationType
import com.vlsu.maps.domain.rx.AppSchedulerProvider
import com.vlsu.maps.navigation.map.MapScreenRouter
import io.reactivex.disposables.Disposables
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class NotifyingPresenter @Inject constructor(
    private val notificationsManager: NotificationsManager,
    private val mapScreenRouter: MapScreenRouter
) : MvpBasePresenter<NotifyingView>() {

    private var notifyingDisposable = Disposables.disposed()

    private var notificationType = NotificationType.UNDEFINED
    private var notification = ""

    override fun attachView(view: NotifyingView) {
        super.attachView(view)
        Timber.d("attachView")
    }

    override fun detachView() {
        notifyingDisposable.dispose()
        super.detachView()
    }

    fun onAcceptBtnClicked() {
        if (notification.isEmpty() || notificationType == NotificationType.UNDEFINED) return
        notifyingDisposable.dispose()
        notifyingDisposable = notificationsManager
            .notify(NotificationItem(notificationType, Date(), notification))
            .subscribeOn(AppSchedulerProvider.io())
            .observeOn(AppSchedulerProvider.ui())
            .subscribe({
                mapScreenRouter.back()
            }, Timber::e)
    }

    fun onNotificationTypeChanged(notificationType: NotificationType) {
        Timber.d("onNotificationTypeChanged $notificationType")
        this.notificationType = notificationType
    }

    fun onNotifyingTextChanged(notification: String) {
        Timber.d("onNotifyingTextChanged $notification")
        this.notification = notification
    }
}