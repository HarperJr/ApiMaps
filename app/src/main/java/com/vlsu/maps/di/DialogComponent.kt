package com.vlsu.maps.di

import com.vlsu.maps.di.scope.DialogScope
import com.vlsu.maps.di.subcomponent.dialog.RegionSelectorComponent
import dagger.Component

@DialogScope
@Component(
    dependencies = [
        AppComponent::class
    ]
)
interface DialogComponent {
    fun regionSelectorComponent(): RegionSelectorComponent
}