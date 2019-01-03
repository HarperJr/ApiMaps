package com.vlsu.maps.ui.mvp

import com.vlsu.maps.ui.mvp.view.MvpView
import com.vlsu.maps.ui.mvp.view.MvpViewState

abstract class ViewState<View : MvpView> : MvpViewState<View> {

    private val attachedViews = mutableListOf<View>()

    override fun attachView(v: View) {
        if (attachedViews.add(v)) {
            onAttachView(v)
        }
    }

    override fun detachView(v: View) {
        if (attachedViews.remove(v)) {
            onDetachView(v)
        }
    }

    abstract fun onAttachView(v: View)

    abstract fun onDetachView(v: View)

    protected fun forEachView(action: (v: View) -> Unit) = attachedViews.forEach(action)
}