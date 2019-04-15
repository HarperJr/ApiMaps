package com.vlsu.maps.presentation.fragment.routing_compose

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class RoutingComposePresenter @Inject constructor(
    private val router: Router
) : MvpBasePresenter<RoutingComposeView>() {

    override fun attachView(view: RoutingComposeView) {
        super.attachView(view)
    }

    override fun detachView() {
        super.detachView()
    }

    fun onBackBtnClicked() {
        router.exit()
    }
}