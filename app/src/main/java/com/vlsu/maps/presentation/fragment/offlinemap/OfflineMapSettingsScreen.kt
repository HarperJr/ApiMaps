package com.vlsu.maps.presentation.fragment.offlinemap

import android.support.v4.app.Fragment
import com.vlsu.maps.navigation.AppNavigation
import ru.terrakok.cicerone.android.support.SupportAppScreen

class OfflineMapSettingsScreen : SupportAppScreen() {

    override fun getScreenKey(): String {
        return AppNavigation.Screen.OFFLINE_MAP_SETTINGS_SCREEN.key
    }

    override fun getFragment(): Fragment {
        return OfflineMapSettingsFragment.newInstance()
    }
}