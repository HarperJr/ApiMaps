package com.vlsu.maps.presentation.fragment.routing_compose

import android.support.v4.app.Fragment
import com.vlsu.maps.navigation.AppNavigation
import ru.terrakok.cicerone.android.support.SupportAppScreen

class RoutingComposeScreen : SupportAppScreen() {

    override fun getScreenKey(): String {
        return AppNavigation.Screen.ROUTING_COMPOSE_SCREEN.key
    }

    override fun getFragment(): Fragment {
        return RoutingComposeFragment.newInstance()
    }
}