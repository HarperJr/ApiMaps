package com.vlsu.maps.ui.mvp.presenter

/**
 * @author Nikita Sychev
 **/
interface MvpPresenter<V> {

    fun bind(v: V)

    fun unbind(v: V)

    fun destroy()
}