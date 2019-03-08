package com.vlsu.maps.di.subcomponent.app

import com.vlsu.maps.presentation.fragment.map.delegate.MapDelegate
import com.vlsu.maps.presentation.fragment.map.MapPresenter
import com.vlsu.maps.presentation.fragment.map.MapViewState
import dagger.Subcomponent

@Subcomponent
interface MapComponent {

    fun mapPresenter(): MapPresenter

    fun mapViewState(): MapViewState

    fun mapDelegate(): MapDelegate
}