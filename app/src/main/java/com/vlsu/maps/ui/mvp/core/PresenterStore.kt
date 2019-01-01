package com.vlsu.maps.ui.mvp.core

import com.vlsu.maps.ui.mvp.presenter.MvpPresenter
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Nikita Sychev
 **/
class PresenterStore @Inject constructor() {

    private val presenters = HashMap<String, MvpPresenter<*>?>()

    fun <P : MvpPresenter<*>> getPresenter(tag: String): P? {
        return presenters[tag] as P
    }

    fun putPresenter(tag: String, presenter: MvpPresenter<*>) {
        presenters[tag] = presenter
    }


    fun removePresenter(tag: String) {
        presenters.remove(tag)
    }
}