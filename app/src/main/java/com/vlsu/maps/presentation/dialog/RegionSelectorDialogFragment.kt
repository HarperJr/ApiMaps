package com.vlsu.maps.presentation.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvp.MvpDialogFragment
import com.vlsu.maps.R
import com.vlsu.maps.di.Dagger

class RegionSelectorDialogFragment :
    MvpDialogFragment<RegionSelectorView, RegionSelectorPresenter>() {

    private val component = Dagger.dialogComponent.regionSelectorComponent()

    override fun createPresenter(): RegionSelectorPresenter {
        return component.regionSelectorPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog_region_selector, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): RegionSelectorDialogFragment = RegionSelectorDialogFragment()
    }
}