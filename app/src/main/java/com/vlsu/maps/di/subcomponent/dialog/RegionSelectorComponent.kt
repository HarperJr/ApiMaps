package com.vlsu.maps.di.subcomponent.dialog

import com.vlsu.maps.presentation.dialog.RegionSelectorDialogFragment
import com.vlsu.maps.presentation.dialog.RegionSelectorPresenter
import dagger.Subcomponent

@Subcomponent
interface RegionSelectorComponent {
    fun inject(regionSelectorDialogFragment: RegionSelectorDialogFragment)

    fun regionSelectorPresenter(): RegionSelectorPresenter
}