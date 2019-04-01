package com.vlsu.maps.presentation.fragment.notification

import android.support.v4.app.Fragment
import com.vlsu.maps.navigation.MapNavigation
import ru.terrakok.cicerone.android.support.SupportAppScreen

class NotificationScreen : SupportAppScreen() {

    override fun getScreenKey(): String {
        return MapNavigation.Screen.NOTIFICATION_SCREEN.key
    }

    override fun getFragment(): Fragment {
        return NotificationFragment.newInstance()
    }
}