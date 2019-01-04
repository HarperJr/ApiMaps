package com.vlsu.maps.ui.activity.main.fragment.mvp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby.mvp.MvpFragment
import com.vlsu.maps.R
import com.vlsu.maps.dagger.Dagger


class MapFragment : MvpFragment<MapView, MapPresenter>(), MapView {

    private val component = Dagger.getComponent()!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun createPresenter(): MapPresenter {
        return component.mapPresenter()
    }

}
