package com.vlsu.maps.presentation.fragment.notifying

import android.support.v4.app.Fragment
import com.vlsu.maps.navigation.map.Navigation
import ru.terrakok.cicerone.android.support.SupportAppScreen

class NotifyingScreen : SupportAppScreen() {

    override fun getScreenKey(): String {
        return Navigation.Screen.NOTIFYING_SCREEN.key
    }

    override fun getFragment(): Fragment {
        return NotifyingFragment.newInstance()
    }
}