package com.vlsu.maps.presentation.fragment.region

import android.support.v4.app.Fragment
import com.vlsu.maps.navigation.Navigation
import ru.terrakok.cicerone.android.support.SupportAppScreen

class RegionScreen : SupportAppScreen() {

    override fun getScreenKey(): String {
        return Navigation.Screen.REGION_SCREEN.key
    }

    override fun getFragment(): Fragment {
        return RegionFragment.newInstance()
    }
}