package com.vlsu.maps.presentation.fragment.info

import android.support.v4.app.Fragment
import com.vlsu.maps.navigation.Navigation
import ru.terrakok.cicerone.android.support.SupportAppScreen

class InfoScreen : SupportAppScreen() {

    override fun getScreenKey(): String {
        return Navigation.Screen.INFO_SCREEN.key
    }

    override fun getFragment(): Fragment {
        return InfoFragment.newInstance()
    }
}