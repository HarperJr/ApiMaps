package com.vlsu.maps.ui.activity.main.fragment.map

import android.support.v4.app.Fragment
import com.vlsu.maps.navigation.Navigation
import ru.terrakok.cicerone.android.support.SupportAppScreen

class MapScreen : SupportAppScreen() {

    override fun getScreenKey(): String {
        return Navigation.Screen.MAP_SCREEN.key
    }

    override fun getFragment(): Fragment {
        return MapFragment.newInstance()
    }

}