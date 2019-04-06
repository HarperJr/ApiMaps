package com.vlsu.maps.presentation.fragment.regions


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateFragment
import com.vlsu.maps.R
import com.vlsu.maps.di.Dagger
import com.vlsu.maps.presentation.fragment.regions.adapter.RegionItem
import com.vlsu.maps.presentation.fragment.regions.adapter.RegionsDelegate
import kotlinx.android.synthetic.main.fragment_region.*


class OfflineMapSettingsFragment :
    MvpViewStateFragment<OfflineMapSettingsView, OfflineMapSettingsPresenter, OfflineMapSettingsViewState>(),
    OfflineMapSettingsView {

    private val component = Dagger.appComponent.offlineMapSettingsComponent()

    private val regionsAdapter by lazy {
        ListDelegationAdapter<List<RegionItem>>(
            AdapterDelegatesManager<List<RegionItem>>()
                .apply {
                    addDelegate(RegionsDelegate(onItemClickListener))
                })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_region, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        region_recycler.adapter = regionsAdapter
    }

    override fun setRegions(regions: List<RegionItem>) {
        regionsAdapter.items = regions
        regionsAdapter.notifyDataSetChanged()
    }

    override fun createPresenter(): OfflineMapSettingsPresenter {
        return component.regionsPresenter()
    }

    override fun createViewState(): OfflineMapSettingsViewState {
        return component.regionsViewState()
    }

    override fun onNewViewStateInstance() {

    }

    private val onItemClickListener = { id: Long ->
        presenter.onRegionSelected(id)
    }

    companion object {
        fun newInstance() = OfflineMapSettingsFragment()
    }
}
