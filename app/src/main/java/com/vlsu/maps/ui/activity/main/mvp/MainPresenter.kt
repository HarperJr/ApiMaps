package com.vlsu.maps.ui.activity.main.mvp

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.vlsu.maps.ui.activity.main.fragment.map.MapScreen
import ru.terrakok.cicerone.Router
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
}
