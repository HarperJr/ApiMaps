package com.vlsu.maps.presentation.activity.main

import android.os.Bundle
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.vlsu.maps.R
import com.vlsu.maps.di.Dagger
import com.vlsu.maps.navigation.AppNavigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class MainActivity : MvpActivity<MainView, MainPresenter>(),
    MainView {

    private val component = Dagger.appComponent.mainComponent()
    @Inject
    lateinit var navigationHolder: NavigatorHolder

    private val navigator by lazy { AppNavigator(this, R.id.mainContainer) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)

        setContentView(R.layout.activity_main)
        presenter.attachView(this)
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

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }
}
