package com.vlsu.maps.presentation.fragment.map

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.mapbox.mapboxsdk.geometry.LatLng
import com.vlsu.maps.di.scope.AppScope
import com.vlsu.maps.domain.interactor.location.LocationUpdatesProvider
import com.vlsu.maps.domain.interactor.notification.NotificationsProvider
import com.vlsu.maps.domain.interactor.offlinemap.MapRegionLoader
import com.vlsu.maps.domain.model.NotificationType
import com.vlsu.maps.domain.rx.AppSchedulerProvider
import com.vlsu.maps.navigation.map.MapScreenRouter
import com.vlsu.maps.presentation.fragment.notification.NotificationScreen
import com.vlsu.maps.presentation.fragment.routing.RoutingScreen
import com.vlsu.maps.presentation.fragment.settings.SettingsScreen
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AppScope
class MapPresenter @Inject constructor(
    private val locationUpdatesProvider: LocationUpdatesProvider,
    private val notificationsProvider: NotificationsProvider,
    private val regionLoader: MapRegionLoader,
    private val mapScreenRouter: MapScreenRouter
) : MvpBasePresenter<MapView>() {

    private var locationUpdatesDisposable = Disposables.disposed()
    private var downloadStatusDisposable = Disposables.disposed()
    private var notificationsDisposable = Disposables.disposed()
    private var hasNotificationsDisposable = Disposables.disposed()
    private var notificationTabRevealTimerDisposable = Disposables.disposed()

    override fun attachView(view: MapView) {
        super.attachView(view)
        downloadStatusDisposable = regionLoader
            .downloadStatus()
            .observeOn(AppSchedulerProvider.ui())
            .doOnNext { view.setProgressVisible(true) }
            .doOnComplete { view.setProgressVisible(false) }
            .subscribe({ progress ->
                Timber.d("loading region, progress: $progress")
                view.setProgress(progress)
            }, Timber::e)
        notificationsDisposable = notificationsProvider.incomingNotifications()
            .subscribeOn(AppSchedulerProvider.io())
            .observeOn(AppSchedulerProvider.ui())
            .subscribe({ notification ->
                ifViewAttached {
                    if (notification.type == NotificationType.COLLAPSE) {
                        it.setNotificationBarRevealed(false)
                        it.showVitalNotification(notification)
                    }
                    it.showNotification(notification)
                }
            }, Timber::e)
    }

    override fun detachView() {
        locationUpdatesProvider.stopUpdates()

        locationUpdatesDisposable.dispose()
        downloadStatusDisposable.dispose()

        super.detachView()
    }

    fun onMapReady() {
        observeLocationUpdates()
    }

    fun onMapMoved() {
        disposeLocationUpdates()
        invalidateState()
    }

    fun onZoomInButtonClicked() {
        ifViewAttached { it.zoomIn() }
    }

    fun onZoomOutButtonClicked() {
        ifViewAttached { it.zoomOut() }
    }

    fun onLocationButtonClicked() {
        observeLocationUpdates()
    }

    fun navigateToNotifications() {
        mapScreenRouter.replace(NotificationScreen())
        ifViewAttached { it.setBottomSheetExpanded(true) }
    }

    fun navigateToSettings() {
        mapScreenRouter.replace(SettingsScreen())
        ifViewAttached { it.setBottomSheetExpanded(true) }
    }

    fun navigateToRouting() {
        mapScreenRouter.replace(RoutingScreen())
        ifViewAttached { it.setBottomSheetExpanded(true) }
    }

    fun onBackPressed() {
        mapScreenRouter.back()
    }

    fun onBottomSheetHidden() {
        mapScreenRouter.back()
    }

    fun onDialogDismissed() {
        setNotificationTabRevealedDelayed(true, NOTIFICATION_TAB_REVEAL_DELAY)
    }

    private fun invalidateState() {
        hasNotificationsDisposable.dispose()
        hasNotificationsDisposable = notificationsProvider.hasUnreadNotifications()
            .subscribeOn(AppSchedulerProvider.io())
            .observeOn(AppSchedulerProvider.ui())
            .subscribe({ hasNotifications ->
                setNotificationTabRevealedDelayed(hasNotifications, NOTIFICATION_TAB_REVEAL_DELAY)
            }, Timber::e)
    }

    private fun setNotificationTabRevealedDelayed(revealed: Boolean, delay: Long) {
        notificationTabRevealTimerDisposable.dispose()
        notificationTabRevealTimerDisposable = Observable.timer(delay, TimeUnit.MILLISECONDS, AppSchedulerProvider.io())
            .observeOn(AppSchedulerProvider.ui())
            .subscribe {
                ifViewAttached { it.setNotificationBarRevealed(revealed) }
            }
    }

    private fun observeLocationUpdates() {
        locationUpdatesProvider.startUpdates()
        locationUpdatesDisposable = locationUpdatesProvider.updates()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { location ->
                LatLng(location.latitude, location.longitude)
            }
            .subscribe { location -> ifViewAttached { it.setOriginLocation(location) } }
        ifViewAttached { it.setOriginBtnActive(true) }
    }

    private fun disposeLocationUpdates() {
        locationUpdatesProvider.stopUpdates()
        locationUpdatesDisposable.dispose()
        hasNotificationsDisposable.dispose()
        notificationTabRevealTimerDisposable.dispose()

        ifViewAttached { it.setOriginBtnActive(false) }
    }


    companion object {
        private const val NOTIFICATION_TAB_REVEAL_DELAY = 1000L
    }
}