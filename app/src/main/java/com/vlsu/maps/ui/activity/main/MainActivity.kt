package com.vlsu.maps.ui.activity.main

import android.Manifest
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import com.vlsu.maps.R
import com.vlsu.maps.dagger.Dagger
import com.vlsu.maps.navigation.MainNavigator
import com.vlsu.maps.ui.activity.main.mvp.MainPresenter
import com.vlsu.maps.ui.activity.main.mvp.MainView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.NavigatorHolder
import timber.log.Timber
import javax.inject.Inject

class MainActivity : MvpActivity<MainView, MainPresenter>(),
    MainView, BottomNavigationView.OnNavigationItemSelectedListener {

    private val component = Dagger.getComponent().mainComponent()
    @Inject
    lateinit var navigationHolder: NavigatorHolder

    private val rxPermissions by lazy { RxPermissions(this) }
    private val navigator by lazy { MainNavigator(this, supportFragmentManager, R.id.mainContainer) }

    private var disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)

        setContentView(R.layout.activity_main)
        bottomNavigationBar.setOnNavigationItemSelectedListener(this)
    }

    override fun createPresenter(): MainPresenter {
        return component.mainPresenter()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuSelectionMap -> {
                presenter.navigateToMap()
                true
            }
            R.id.menuSelectionNotifications -> {
                presenter.navigateToNotifications()
                true
            }
            R.id.menuSelectionInfo -> {
                presenter.navigateToInfo()
                true
            }
            else -> false
        }
    }

    override fun onResume() {
        rxPermissions
            .request(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            .subscribe { granted ->
                if (!granted) {
                    Timber.d("Permissions not granted")
                }
            }
            .disposeOnDestroy()
        super.onResume()
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
        disposable.dispose()
        super.onDestroy()
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    private fun Disposable.disposeOnDestroy(): Disposable {
        disposable.add(this)
        return this
    }
}
