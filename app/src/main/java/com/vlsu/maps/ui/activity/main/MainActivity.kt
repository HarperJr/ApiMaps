package com.vlsu.maps.ui.activity.main

import android.os.Bundle
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.vlsu.maps.R
import com.vlsu.maps.dagger.Dagger
import com.vlsu.maps.ui.activity.main.mvp.MainPresenter
import com.vlsu.maps.ui.activity.main.mvp.MainView

class MainActivity : MvpActivity<MainView, MainPresenter>(),
    MainView {

    private val component = Dagger.getComponent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.openMap()
    }

    override fun createPresenter(): MainPresenter {
        return component.mainPresenter()
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }
}
