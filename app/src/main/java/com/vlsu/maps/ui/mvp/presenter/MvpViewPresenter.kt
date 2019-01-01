package com.vlsu.maps.ui.mvp.presenter

import com.vlsu.maps.ui.mvp.view.MvpView

/**
 * @author Nikita Sychev
 **/
abstract class MvpViewPresenter<View : MvpView> : MvpPresenter<View> {

    var initialized: Boolean = false

    protected var view: View? = null

    fun initialize() {
        if (!initialized) {
            initialized = true
            onInitialized()
        }
    }

    abstract fun onInitialized()

    abstract fun onDestroyed()

    override fun bind(v: View) {
        view = v
    }

    override fun unbind(v: View) {
        view = null
    }

    override fun destroy() {

    }
}