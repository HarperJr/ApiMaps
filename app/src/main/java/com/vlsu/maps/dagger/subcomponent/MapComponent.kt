package com.vlsu.maps.dagger.subcomponent

import com.vlsu.maps.ui.activity.main.fragment.map.MapDelegate
import com.vlsu.maps.ui.activity.main.fragment.map.mvp.MapPresenter
import com.vlsu.maps.ui.activity.main.fragment.map.mvp.MapViewState
import dagger.Subcomponent

@Subcomponent
interface MapComponent {

    fun mapPresenter(): MapPresenter

    fun mapViewState(): MapViewState

    fun mapDelegate(): MapDelegate
}