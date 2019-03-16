package com.vlsu.maps.presentation.fragment.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import com.vlsu.maps.R
import com.vlsu.maps.di.Dagger
import com.vlsu.maps.presentation.fragment.info.mvp.InfoPresenter
import com.vlsu.maps.presentation.fragment.info.mvp.InfoView

class SettingsFragment : MvpFragment<InfoView, InfoPresenter>(), InfoView {

    private val component = Dagger.appComponent.settingsComponent()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun createPresenter(): InfoPresenter {
        return component.infoPresenter()
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}
