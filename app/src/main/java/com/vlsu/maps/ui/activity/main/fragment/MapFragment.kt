package com.vlsu.maps.ui.activity.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby.mvp.MvpFragment
import com.vlsu.maps.dagger.Dagger
import com.vlsu.maps.ui.activity.main.fragment.mvp.MapPresenter
import com.vlsu.maps.ui.activity.main.fragment.mvp.MapView

class MapFragment : MvpFragment<MapView, MapPresenter>(), MapView {

    private val component = Dagger.getComponent()!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun createPresenter(): MapPresenter {
        return component.mapPresenter()
    }

}