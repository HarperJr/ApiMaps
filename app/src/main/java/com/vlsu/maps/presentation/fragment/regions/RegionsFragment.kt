package com.vlsu.maps.presentation.fragment.regions


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vlsu.maps.R
import com.vlsu.maps.presentation.fragment.regions.adapter.RegionItem


class RegionsFragment : Fragment(), RegionsView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_region, container, false)
    }

    override fun setRegions(regions: List<RegionItem>) {

    }

    companion object {
        fun newInstance() = RegionsFragment()
    }
}
