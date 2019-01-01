package com.vlsu.maps.ui.mvp.view

interface MvpViewState<View : MvpView> {

    fun attachView(v: View)

    fun detachView(v: View)
}