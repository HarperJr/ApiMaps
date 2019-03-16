package com.vlsu.maps.navigation

import com.vlsu.maps.presentation.fragment.info.SettingsScreen
import com.vlsu.maps.presentation.fragment.map.MapScreen
import com.vlsu.maps.presentation.fragment.notification.NotificationScreen
import com.vlsu.maps.presentation.fragment.region.RegionScreen

class Navigation {

    enum class Screen(val key: String) {
        MAP_SCREEN("MAP_SCREEN"),
        NOTIFICATION_SCREEN("NOTIFICATION_SCREEN"),
        REGION_SCREEN("REGION_SCREEN"),
        SETTINGS_SCREEN("SETTINGS_SCREEN");

        companion object {
            val DEFAULT_SCREEN = MAP_SCREEN

            fun of(key: String? = null): Screen = values().firstOrNull { it.key == key } ?: DEFAULT_SCREEN

            fun getScreen(screen: Screen) = when (screen) {
                MAP_SCREEN -> MapScreen()
                NOTIFICATION_SCREEN -> NotificationScreen()
                REGION_SCREEN -> RegionScreen()
                SETTINGS_SCREEN -> SettingsScreen()
            }
        }
    }
}