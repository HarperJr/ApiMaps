package com.vlsu.maps.navigation

import com.vlsu.maps.presentation.fragment.map.MapScreen
import com.vlsu.maps.presentation.fragment.region.RegionScreen

class Navigation {
    enum class Screen(val key: String) {
        MAP_SCREEN("MAP_SCREEN"),
        REGION_SCREEN("REGION_SCREEN")
    }

    companion object {
        val DEFAULT_SCREEN = Screen.MAP_SCREEN

        fun key(key: String? = null): Screen = Screen.values().firstOrNull { it.key == key } ?: DEFAULT_SCREEN

        fun screen(screen: Screen) = when (screen) {
            Screen.MAP_SCREEN -> MapScreen()
            Screen.REGION_SCREEN -> RegionScreen()
        }
    }
}