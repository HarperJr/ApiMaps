package com.vlsu.maps.di.subcomponent.app

import com.vlsu.maps.presentation.fragment.settings.SettingsFragment
import dagger.Subcomponent

@Subcomponent
interface SettingsComponent {
    fun inject(settingsFragment: SettingsFragment)
}