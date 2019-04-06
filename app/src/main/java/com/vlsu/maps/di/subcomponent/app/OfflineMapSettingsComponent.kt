package com.vlsu.maps.di.subcomponent.app

import com.vlsu.maps.presentation.fragment.regions.OfflineMapSettingsPresenter
import com.vlsu.maps.presentation.fragment.regions.OfflineMapSettingsViewState
import dagger.Subcomponent

@Subcomponent
interface OfflineMapSettingsComponent {
    fun regionsPresenter(): OfflineMapSettingsPresenter

    fun regionsViewState(): OfflineMapSettingsViewState
}