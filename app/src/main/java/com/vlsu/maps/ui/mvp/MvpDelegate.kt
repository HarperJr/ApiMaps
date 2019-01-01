package com.vlsu.maps.ui.mvp

import android.os.Bundle
import com.vlsu.maps.ui.mvp.core.MvpProcessor
import com.vlsu.maps.ui.mvp.presenter.MvpPresenter
import com.vlsu.maps.ui.mvp.view.MvpView

/**
 * @author Nikita Sychev
 **/
class MvpDelegate(
    private val mvpProcessor: MvpProcessor,
    private val view: MvpView
) {

    private val childDelegates = HashMap<String, MvpDelegate>()
    private val presenters = HashMap<MvpPresenter<MvpView>, String>()
    private lateinit var delegateTag: String
    private var keepAliveProvider: KeepAliveProvider? = null

    fun init(savedInstanceState: Bundle?) {
        delegateTag = when (savedInstanceState) {
            null -> view.toString()
            else -> savedInstanceState.getString(DELEGATE_TAG)
        }
    }

    fun <P : MvpPresenter<MvpView>> getPresenter(presenterProvider: PresenterProvider<P>, tag: String): P {
        val presenter = mvpProcessor.getPresenter(presenterProvider, tag)
        presenters[presenter] = tag
        return presenter
    }

    fun <P : MvpPresenter<MvpView>> getPresenter(presenterProvider: PresenterProvider<P>, presenterClass: Class<P>): P {
        val tag = "${delegateTag}_${presenterClass.name}"
        return getPresenter(presenterProvider, tag)
    }

    fun saveState(outState: Bundle?) {
        outState?.putString(DELEGATE_TAG, delegateTag)
    }

    fun setKeepAliveProvider(keepAliveProvider: KeepAliveProvider) {
        this.keepAliveProvider = keepAliveProvider
    }

    fun destroy(keepAlive: Boolean) {
        childDelegates.forEach { (_, delegate) ->
            delegate.destroy(keepAliveProvider?.keepAlive(keepAlive) ?: keepAlive)
        }
    }

    fun bindView() {
        childDelegates.forEach { (_, delegate) ->
            delegate.bindView()
        }
        presenters.forEach { (presenter, _) ->
            presenter.bind(view)
        }
    }

    fun unbindView() {
        childDelegates.forEach { (_, delegate) ->
            delegate.unbindView()
        }
        presenters.forEach { (presenter, _) ->
            presenter.unbind(view)
        }
    }

    companion object {
        private const val DELEGATE_TAG = "MVP_DELEGATE_TAG"
    }
}