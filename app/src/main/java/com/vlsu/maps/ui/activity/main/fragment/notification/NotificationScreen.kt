package com.vlsu.maps.ui.activity.main.fragment.notification

import android.support.v4.app.Fragment
import com.vlsu.maps.navigation.Navigation
import ru.terrakok.cicerone.android.support.SupportAppScreen

class NotificationScreen : SupportAppScreen() {

    override fun getScreenKey(): String {
        return Navigation.Screen.NOTIFICATION_SCREEN.key
    }

    override fun getFragment(): Fragment {
        return NotificationFragment.newInstance()
    }
}