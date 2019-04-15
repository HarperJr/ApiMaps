package com.vlsu.maps.di.subcomponent.app

import com.vlsu.maps.presentation.fragment.routing.RoutingFragment
import com.vlsu.maps.presentation.fragment.routing.RoutingPresenter
import com.vlsu.maps.presentation.fragment.routing.RoutingViewState
import dagger.Subcomponent

@Subcomponent
interface RoutingComponent {
    fun inject(routingFragment: RoutingFragment)

    fun routingPresenter(): RoutingPresenter

    fun routingViewState(): RoutingViewState
}