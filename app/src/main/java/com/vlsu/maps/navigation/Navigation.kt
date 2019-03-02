package com.vlsu.maps.navigation

import com.vlsu.maps.ui.activity.main.fragment.info.InfoScreen
import com.vlsu.maps.ui.activity.main.fragment.map.MapScreen
import com.vlsu.maps.ui.activity.main.fragment.notification.NotificationScreen

class Navigation {

    enum class Screen(val key: String) {
        MAP_SCREEN("MAP_SCREEN"),
        NOTIFICATION_SCREEN("NOTIFICATION_SCREEN"),
        INFO_SCREEN("INFO_SCREEN");

        companion object {
            val DEFAULT_SCREEN = MAP_SCREEN

            fun of(key: String? = null): Screen = values().firstOrNull { it.key == key } ?: DEFAULT_SCREEN

            fun getScreen(screen: Screen) = when (screen) {
                MAP_SCREEN -> MapScreen()
                NOTIFICATION_SCREEN -> NotificationScreen()
                INFO_SCREEN -> InfoScreen()
            }
        }
    }
}