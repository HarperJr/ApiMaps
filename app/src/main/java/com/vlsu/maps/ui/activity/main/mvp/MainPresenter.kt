package com.vlsu.maps.ui.activity.main.mvp

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.vlsu.maps.ui.activity.main.fragment.info.InfoScreen
import com.vlsu.maps.ui.activity.main.fragment.map.MapScreen
import com.vlsu.maps.ui.activity.main.fragment.notification.NotificationScreen
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val router: Router
) : MvpBasePresenter<MainView>() {

    override fun attachView(view: MainView?) {
        super.attachView(view)
        router.navigateTo(MapScreen())
    }

    fun onBackPressed() {
        router.exit()
    }

    fun navigateToMap() {
        Timber.d("navigateToMap")
        router.navigateTo(MapScreen())
    }

    fun navigateToNotifications() {
        Timber.d("navigateToNotifications")
        router.navigateTo(NotificationScreen())
    }

    fun navigateToInfo() {
        Timber.d("navigateToInfo")
        router.navigateTo(InfoScreen())
    }
}
