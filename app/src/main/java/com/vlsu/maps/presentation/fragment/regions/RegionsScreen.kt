package com.vlsu.maps.presentation.fragment.regions

import android.support.v4.app.Fragment
import com.vlsu.maps.navigation.Navigation
import ru.terrakok.cicerone.android.support.SupportAppScreen

class RegionsScreen : SupportAppScreen() {

    override fun getScreenKey(): String {
        return Navigation.Screen.REGIONS_SCREEN.key
    }

    override fun getFragment(): Fragment {
        return RegionsFragment.newInstance()
    }
}