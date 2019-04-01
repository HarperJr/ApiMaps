package com.vlsu.maps.presentation.activity.main

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.vlsu.maps.presentation.fragment.map.MapScreen
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val router: Router
) : MvpBasePresenter<MainView>() {

    override fun attachView(view: MainView) {
        super.attachView(view)
        router.navigateTo(MapScreen())
    }

    fun onBackPressed() {
        router.exit()
    }
}
