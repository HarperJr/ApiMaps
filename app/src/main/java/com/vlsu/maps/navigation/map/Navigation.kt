package com.vlsu.maps.navigation.map

import com.vlsu.maps.presentation.fragment.notification.NotificationScreen
import com.vlsu.maps.presentation.fragment.routing.RoutingScreen
import com.vlsu.maps.presentation.fragment.settings.SettingsScreen

class Navigation {

    enum class Screen(val key: String) {
        SETTINGS_SCREEN("SETTINGS_SCREEN"),
        ROUTING_SCREEN("ROUTING_SCREEN"),
        NOTIFICATION_SCREEN("NOTIFICATION_SCREEN")
    }

    companion object {
        val DEFAULT_SCREEN = Screen.NOTIFICATION_SCREEN

        fun key(key: String? = null) = Screen.values().firstOrNull { it.key == key } ?: DEFAULT_SCREEN

        fun screen(screen: Screen) = when (screen) {
            Screen.SETTINGS_SCREEN -> SettingsScreen()
            Screen.ROUTING_SCREEN -> RoutingScreen()
            Screen.NOTIFICATION_SCREEN -> NotificationScreen()
        }
    }
}