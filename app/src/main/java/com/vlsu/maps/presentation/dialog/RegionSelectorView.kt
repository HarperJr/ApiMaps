package com.vlsu.maps.presentation.dialog

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.vlsu.maps.presentation.fragment.notification.adapter.RegionItem

interface RegionSelectorView : MvpView {
    fun setRegions(regions: List<RegionItem>)
}