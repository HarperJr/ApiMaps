package com.vlsu.maps.ui.activity.main.fragment.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby.mvp.MvpFragment
import com.vlsu.maps.R
import com.vlsu.maps.dagger.Dagger
import com.vlsu.maps.ui.activity.main.fragment.info.mvp.InfoPresenter
import com.vlsu.maps.ui.activity.main.fragment.info.mvp.InfoView

class InfoFragment : MvpFragment<InfoView, InfoPresenter>(), InfoView {

    private val component = Dagger.getComponent().infoComponent()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun createPresenter(): InfoPresenter {
        return component.infoPresenter()
    }

    companion object {
        fun newInstance() = InfoFragment()
    }
}
