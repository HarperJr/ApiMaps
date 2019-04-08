package com.vlsu.maps.presentation.fragment.settings

import android.support.v4.app.Fragment
import com.vlsu.maps.navigation.map.Navigation
import ru.terrakok.cicerone.android.support.SupportAppScreen

class SettingsScreen : SupportAppScreen() {

    override fun getScreenKey(): String {
        return Navigation.Screen.SETTINGS_SCREEN.key
    }

    override fun getFragment(): Fragment {
        return SettingsFragment.newInstance()
    }
}