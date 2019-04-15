package com.vlsu.maps.presentation.fragment.offlinemap


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateFragment
import com.vlsu.maps.R
import com.vlsu.maps.di.Dagger
import com.vlsu.maps.presentation.dialog.AcceptDialog
import com.vlsu.maps.presentation.fragment.offlinemap.adapter.RegionItem
import com.vlsu.maps.presentation.fragment.offlinemap.adapter.RegionsDelegate
import kotlinx.android.synthetic.main.fragment_offline_map_settings.*


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
        return inflater.inflate(R.layout.fragment_offline_map_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        offline_map_recycler.adapter = regionsAdapter
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

    override fun showDialog(region: String) {
        acceptDialog(region, presenter::onAcceptBtnClicked)
            .show(childFragmentManager, REGION_DOWNLOAD_ACCEPT_DIALOG_TAG)
    }

    private fun acceptDialog(title: String, acceptBtnAction: () -> Unit): AcceptDialog {
        return AcceptDialog.newInstance(
            title = title,
            subtitle = getString(R.string.settings_offline_map_dialog_subtitle),
            acceptLabel = getString(R.string.settings_offline_map_dialog_accept)
        ).apply { acceptBtnClickListener = acceptBtnAction }
    }

    private val onItemClickListener = { item: RegionItem -> presenter.onRegionSelected(item) }

    companion object {
        fun newInstance() = OfflineMapSettingsFragment()

        private const val REGION_DOWNLOAD_ACCEPT_DIALOG_TAG = "REGION_DOWNLOAD_ACCEPT_DIALOG_TAG"
    }
}