package com.vlsu.maps.di

import com.vlsu.maps.di.scope.DialogScope
import dagger.Component

@DialogScope
@Component(
    dependencies = [
        AppComponent::class
    ]
)
interface DialogComponent {
    
}