package com.vlsu.maps.presentation.fragment.settings

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vlsu.maps.R
import com.vlsu.maps.di.Dagger
import com.vlsu.maps.presentation.fragment.regions.OfflineMapSettingsScreen
import kotlinx.android.synthetic.main.fragment_settings.*
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class SettingsFragment : Fragment() {

    private val component = Dagger.appComponent.settingsComponent()
    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        settings_offline_map_case.setOnClickListener { onSettingsCaseSelected(R.id.settings_offline_map_case) }
    }

    private fun onSettingsCaseSelected(caseId: Int) {
        when (caseId) {
            R.id.settings_offline_map_case -> router.navigateTo(OfflineMapSettingsScreen())
            else -> Timber.e("Unsupported settings case")
        }
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}
