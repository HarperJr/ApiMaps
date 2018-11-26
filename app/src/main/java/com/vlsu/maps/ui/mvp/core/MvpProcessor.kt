package com.vlsu.maps.ui.mvp.core

import com.vlsu.maps.ui.mvp.presenter.MvpPresenter
import com.vlsu.maps.ui.mvp.PresenterProvider
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Nikita Sychev
 **/
@Singleton
class MvpProcessor @Inject constructor(
    private val presenterStore: PresenterStore,
    private val presenterCounter: PresenterCounter
) {

    fun <P : MvpPresenter> getPresenter(presenterProvider: PresenterProvider<P>, tag: String): P {
        var presenter: P? = presenterStore.getPresenter<P>(tag)
        if (presenter == null) {
            presenter = presenterProvider.providePresenter()
            presenterStore.putPresenter(tag, presenter)
        }
        presenterCounter.incrementCounter(tag)
        return presenter
    }

    fun <P : MvpPresenter> freePresenter(presenter: P, tag: String, keepAlive: Boolean) {
        if (presenterCounter.decrementCounter(tag)) {
            if (!keepAlive) {
                presenterStore.removePresenter(tag)
                presenter.destroy()
            }
        }
    }
}