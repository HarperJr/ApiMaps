package com.vlsu.maps.ui.activity.main.fragment.map

import android.support.v4.app.Fragment
import ru.terrakok.cicerone.Screen

class MapScreen : Screen() {

    fun getFragment(): Fragment {
        return MapFragment()
    }

    override fun getScreenKey(): String {
        return super.getScreenKey()
    }
}