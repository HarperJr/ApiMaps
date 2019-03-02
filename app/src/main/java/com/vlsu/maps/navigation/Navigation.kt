package com.vlsu.maps.navigation

import com.vlsu.maps.ui.activity.main.fragment.control.ControlScreen
import com.vlsu.maps.ui.activity.main.fragment.map.MapScreen

class Navigation {

    enum class Screen(val key: String) {
        MAP_SCREEN("MAP_SCREEN"),
        CONTROL_SCREEN("CONTROL_SCREEN");

        companion object {
            val DEFAULT_SCREEN = MAP_SCREEN

            fun of(key: String? = null): Screen = values().firstOrNull { it.key == key } ?: DEFAULT_SCREEN

            fun getScreen(screen: Screen) = when (screen) {
                MAP_SCREEN -> MapScreen()
                CONTROL_SCREEN -> ControlScreen()
            }
        }
    }
}