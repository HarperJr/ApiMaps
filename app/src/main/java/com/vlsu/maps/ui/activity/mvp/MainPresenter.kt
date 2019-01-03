package com.vlsu.maps.ui.activity.mvp

import com.vlsu.maps.ui.mvp.presenter.MvpViewStatePresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(mainView: MainViewState) :
    MvpViewStatePresenter<MainView, MainViewState>(mainView) {

    override fun onInitialize() {

    }

    override fun destroy() {

    }
}