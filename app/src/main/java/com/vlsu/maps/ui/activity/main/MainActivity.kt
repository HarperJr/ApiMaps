package com.vlsu.maps.ui.activity.main

import android.os.Bundle
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.vlsu.maps.R
import com.vlsu.maps.dagger.Dagger
import com.vlsu.maps.navigation.MainNavigator
import com.vlsu.maps.ui.activity.main.mvp.MainPresenter
import com.vlsu.maps.ui.activity.main.mvp.MainView
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class MainActivity : MvpActivity<MainView, MainPresenter>(),
    MainView {

    private val component = Dagger.getComponent().mainComponent()
    @Inject
    lateinit var navigationHolder: NavigatorHolder

    private val navigator by lazy { MainNavigator(this, supportFragmentManager, R.id.mainContainer) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        setContentView(R.layout.activity_main)
    }

    override fun createPresenter(): MainPresenter {
        return component.mainPresenter()
    }

    override fun onResumeFragments() {
        navigationHolder.setNavigator(navigator)
        super.onResumeFragments()
    }

    override fun onPause() {
        navigationHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }
}
