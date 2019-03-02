package com.vlsu.maps.ui.activity.main.fragment.control

import android.support.v4.app.Fragment
import com.vlsu.maps.navigation.Navigation
import ru.terrakok.cicerone.android.support.SupportAppScreen

class ControlScreen : SupportAppScreen() {

    override fun getScreenKey(): String {
        return Navigation.Screen.CONTROL_SCREEN.key
    }

    override fun getFragment(): Fragment {
        return InfoFragment.newInstance()
    }
}