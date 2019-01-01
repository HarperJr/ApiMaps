package com.vlsu.maps.ui.mvp.presenter

import com.vlsu.maps.ui.mvp.view.MvpView
import com.vlsu.maps.ui.mvp.view.MvpViewState

abstract class MvpViewStatePresenter<View : MvpView, ViewState: MvpViewState<View>>(private val viewState: MvpViewState<View>) : MvpPresenter<View> {

    private var view: View = viewState as View
    private var initialized = false

    fun initialize() {
        if (!initialized) {
            initialized = true
            onInitialize()
        }
    }

    abstract fun onInitialize()

    override fun bind(v: View) {
        viewState.attachView(v)
    }


    override fun unbind(v: View) {
        viewState.detachView(v)
    }

    override fun destroy() {

    }
}