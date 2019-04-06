package com.vlsu.maps.presentation.fragment.map

import android.support.v4.app.Fragment
import com.vlsu.maps.navigation.AppNavigation
import ru.terrakok.cicerone.android.support.SupportAppScreen

class MapScreen : SupportAppScreen() {

    override fun getScreenKey(): String {
        return AppNavigation.Screen.MAP_SCREEN.key
    }

    override fun getFragment(): Fragment {
        return MapFragment.newInstance()
    }

}