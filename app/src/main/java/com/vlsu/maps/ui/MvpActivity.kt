package com.vlsu.maps.ui

import android.os.Bundle
import android.os.PersistableBundle
import com.vlsu.maps.ui.mvp.MvpDelegate
import com.vlsu.maps.ui.mvp.view.MvpView

open class MvpActivity : AbstractActivity() {

    private lateinit var mvpDelegate: MvpDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mvpDelegate = MvpDelegate(component.getMvpProcessor(), this as MvpView)
            .apply {
                init(savedInstanceState)
            }
    }

    fun getMvpDelegate() = mvpDelegate

    override fun onStart() {
        super.onStart()
        mvpDelegate.bindView()
    }

    override fun onStop() {
        mvpDelegate.unbindView()
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        mvpDelegate.saveState(outState)
    }

    override fun onDestroy() {
        mvpDelegate.destroy(!isFinishing)
        super.onDestroy()
    }
}