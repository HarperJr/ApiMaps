package com.vlsu.maps.navigation

import com.vlsu.maps.presentation.fragment.intro.IntroScreen
import com.vlsu.maps.presentation.fragment.map.MapScreen
import com.vlsu.maps.presentation.fragment.regions.OfflineMapSettingsScreen

class AppNavigation {
    enum class Screen(val key: String) {
        INTRO_SCREEN("INTRO_SCREEN"),
        MAP_SCREEN("MAP_SCREEN"),
        OFFLINE_MAP_SETTINGS_SCREEN("OFFLINE_MAP_SETTINGS_SCREEN")
    }

    companion object {
        fun key(key: String? = null): Screen = Screen.values().firstOrNull { it.key == key } ?:
                throw IllegalArgumentException("Invalid screen key $key")

        fun screen(screen: Screen) = when (screen) {
            Screen.MAP_SCREEN -> MapScreen()
            Screen.OFFLINE_MAP_SETTINGS_SCREEN -> OfflineMapSettingsScreen()
            Screen.INTRO_SCREEN -> IntroScreen()
        }
    }
}