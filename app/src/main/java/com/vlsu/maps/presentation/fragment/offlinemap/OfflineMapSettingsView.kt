package com.vlsu.maps.presentation.fragment.offlinemap

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.vlsu.maps.presentation.fragment.offlinemap.adapter.RegionItem

interface OfflineMapSettingsView : MvpView {
    fun setRegions(regions: List<RegionItem>)
}