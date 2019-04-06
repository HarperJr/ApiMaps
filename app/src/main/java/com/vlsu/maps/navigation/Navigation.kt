package com.vlsu.maps.navigation

import com.vlsu.maps.presentation.fragment.intro.IntroScreen
import com.vlsu.maps.presentation.fragment.map.MapScreen
import com.vlsu.maps.presentation.fragment.regions.RegionsScreen

class Navigation {
    enum class Screen(val key: String) {
        INTRO_SCREEN("INTRO_SCREEN"),
        MAP_SCREEN("MAP_SCREEN"),
        REGIONS_SCREEN("REGIONS_SCREEN")
    }

    companion object {
        val DEFAULT_SCREEN = Screen.MAP_SCREEN

        fun key(key: String? = null): Screen = Screen.values().firstOrNull { it.key == key } ?: DEFAULT_SCREEN

        fun screen(screen: Screen) = when (screen) {
            Screen.MAP_SCREEN -> MapScreen()
            Screen.REGIONS_SCREEN -> RegionsScreen()
            Screen.INTRO_SCREEN -> IntroScreen()
        }
    }
}