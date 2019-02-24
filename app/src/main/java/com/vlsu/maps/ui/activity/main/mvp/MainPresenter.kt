package com.vlsu.maps.ui.activity.main.mvp

import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.vlsu.maps.ui.activity.main.fragment.map.MapScreen
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val router: Router
) : MvpPresenter<MainView> {

    override fun attachView(view: MainView?) {
        router.navigateTo(MapScreen())
    }

    override fun detachView(retainInstance: Boolean) {

    }

    fun openMap() {
        router.navigateTo(MapScreen())
    }

    fun onBackPressed() {
        router.exit()
    }
}
