package com.vlsu.maps.presentation.fragment.intro

import android.support.v4.app.Fragment
import com.vlsu.maps.navigation.AppNavigation
import ru.terrakok.cicerone.android.support.SupportAppScreen

class IntroScreen : SupportAppScreen() {

    override fun getScreenKey(): String {
        return AppNavigation.Screen.INTRO_SCREEN.key
    }

    override fun getFragment(): Fragment {
        return IntroFragment.newInstance()
    }
}