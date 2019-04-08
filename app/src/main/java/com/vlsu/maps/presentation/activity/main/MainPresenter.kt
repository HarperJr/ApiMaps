package com.vlsu.maps.presentation.activity.main

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.vlsu.maps.di.scope.AppScope
import com.vlsu.maps.presentation.fragment.intro.IntroScreen
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@AppScope
class MainPresenter @Inject constructor(
    private val appRouter: Router
) : MvpBasePresenter<MainView>() {

    override fun attachView(view: MainView) {
        super.attachView(view)
        appRouter.navigateTo(IntroScreen())
    }

    fun onBackPressed() {
        appRouter.exit()
    }
}
