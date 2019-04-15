package com.vlsu.maps.di.subcomponent

import com.vlsu.maps.presentation.fragment.routing_compose.RoutingComposeFragment
import com.vlsu.maps.presentation.fragment.routing_compose.RoutingComposePresenter
import com.vlsu.maps.presentation.fragment.routing_compose.RoutingComposeViewState
import dagger.Subcomponent

@Subcomponent
interface RoutingComposeComponent {
    fun inject(routingComposeFragment: RoutingComposeFragment)

    fun routingComposePresenter(): RoutingComposePresenter

    fun routingComposeViewState(): RoutingComposeViewState
}
