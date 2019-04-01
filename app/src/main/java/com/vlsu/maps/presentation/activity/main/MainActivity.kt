package com.vlsu.maps.presentation.activity.main

import android.Manifest
import android.os.Bundle
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import com.vlsu.maps.R
import com.vlsu.maps.di.Dagger
import com.vlsu.maps.navigation.MainNavigator
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.terrakok.cicerone.NavigatorHolder
import timber.log.Timber
import javax.inject.Inject

class MainActivity : MvpActivity<MainView, MainPresenter>(),
    MainView {

    private val component = Dagger.appComponent.mainComponent()
    @Inject
    lateinit var navigationHolder: NavigatorHolder

    private val rxPermissions by lazy { RxPermissions(this) }
    private val navigator by lazy { MainNavigator(this, supportFragmentManager, R.id.mainContainer) }

    private var disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)

        setContentView(R.layout.activity_main)
        requestPermissionsRx()
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
        disposable.dispose()
        super.onDestroy()
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    private fun requestPermissionsRx() {
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
    }

    private fun Disposable.disposeOnDestroy(): Disposable {
        disposable.add(this)
        return this
    }
}
