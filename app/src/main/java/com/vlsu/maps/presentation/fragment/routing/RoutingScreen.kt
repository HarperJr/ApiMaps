package com.vlsu.maps.presentation.fragment.routing

import android.support.v4.app.Fragment
import com.vlsu.maps.navigation.map.Navigation
import ru.terrakok.cicerone.android.support.SupportAppScreen

class RoutingScreen : SupportAppScreen() {

    override fun getScreenKey(): String {
        return Navigation.Screen.ROUTING_SCREEN.key
    }

    override fun getFragment(): Fragment {
        return RoutingFragment.newInstance()
    }
}