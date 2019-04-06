package com.vlsu.maps.presentation.fragment.regions

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.vlsu.maps.presentation.fragment.regions.adapter.RegionItem

interface RegionsView : MvpView {
    fun setRegions(regions: List<RegionItem>)
}