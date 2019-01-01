package com.vlsu.maps.ui.mvp.presenter

import com.vlsu.maps.ui.mvp.view.MvpView

/**
 * @author Nikita Sychev
 **/
interface MvpPresenter<View : MvpView> {

    fun bind(v: View)

    fun unbind(v: View)

    fun destroy()
}