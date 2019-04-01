package com.vlsu.maps.presentation.fragment.routing

import android.support.v4.app.Fragment
import com.vlsu.maps.navigation.MapNavigation
import ru.terrakok.cicerone.android.support.SupportAppScreen

class RoutingScreen : SupportAppScreen() {

    override fun getScreenKey(): String {
        return MapNavigation.Screen.ROUTING_SCREEN.key
    }

    override fun getFragment(): Fragment {
        return RoutingFragment.newInstance()
    }
}