package com.vlsu.maps.presentation.fragment.routing

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.vlsu.maps.presentation.fragment.routing_compose.RoutingComposeScreen
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class RoutingPresenter @Inject constructor(
    private val router: Router
) : MvpBasePresenter<RoutingView>() {

    override fun attachView(view: RoutingView) {
        super.attachView(view)
    }

    override fun detachView() {
        super.detachView()
    }

    fun onComposeBtnClicked() {
        router.navigateTo(RoutingComposeScreen())
    }
}
