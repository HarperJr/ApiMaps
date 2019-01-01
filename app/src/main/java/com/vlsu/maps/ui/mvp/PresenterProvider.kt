package com.vlsu.maps.ui.mvp

import com.vlsu.maps.ui.mvp.presenter.MvpPresenter

/**
 * @author Nikita Sychev
 **/
interface PresenterProvider<P : MvpPresenter<*>> {
    fun providePresenter(): P
}