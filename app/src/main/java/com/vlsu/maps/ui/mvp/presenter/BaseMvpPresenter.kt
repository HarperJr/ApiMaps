package com.vlsu.maps.ui.mvp.presenter

import com.vlsu.maps.ui.mvp.view.MVPView

/**
 * @author Nikita Sychev
 **/
abstract class BaseMvpPresenter<V : MVPView> : MvpPresenter<V> {

    var initialized: Boolean = false

    protected var view: V? = null

    fun initialize() {
        if (!initialized) {
            initialized = true
            onInitialized()
        }
    }

    abstract fun onInitialized();

    override fun bind(v: V) {
        view = v
    }

    override fun unbind(v: V) {
        view = null
    }

    override fun destroy() {

    }
}