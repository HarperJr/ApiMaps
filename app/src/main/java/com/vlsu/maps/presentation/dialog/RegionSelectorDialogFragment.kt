package com.vlsu.maps.presentation.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import com.hannesdorfmann.mosby3.mvp.MvpDialogFragment
import com.vlsu.maps.R
import com.vlsu.maps.di.Dagger
import com.vlsu.maps.presentation.fragment.notification.adapter.RegionDelegate
import com.vlsu.maps.presentation.fragment.notification.adapter.RegionItem
import kotlinx.android.synthetic.main.fragment_dialog_region_selector.*

class RegionSelectorDialogFragment :
    MvpDialogFragment<RegionSelectorView, RegionSelectorPresenter>(), RegionSelectorView {

    private val component = Dagger.dialogComponent.regionSelectorComponent()
    private val regionsAdapter by lazy {
        ListDelegationAdapter<List<RegionItem>>(
            AdapterDelegatesManager<List<RegionItem>>()
                .apply {
                    addDelegate(RegionDelegate().apply { onItemClickListener = { presenter.onRegionItemClicked(it) } })
                })
    }
    private var onDismissListener: (() -> Unit)? = null

    override fun createPresenter(): RegionSelectorPresenter {
        return component.regionSelectorPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog_region_selector, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog_region_selector_recycler.apply {
            adapter = regionsAdapter
        }
        dialog_region_selector_apply_button.setOnClickListener { presenter.onApplyClicked() }
    }

    override fun onDismiss(dialog: DialogInterface) {
        onDismissListener?.invoke()
        super.onDismiss(dialog)
    }

    override fun setRegions(regions: List<RegionItem>) {
        regionsAdapter.items = regions
    }

    companion object {
        fun newInstance(): RegionSelectorDialogFragment = RegionSelectorDialogFragment()
    }
}