package com.vlsu.maps.app

import android.app.Application
import com.mapbox.mapboxsdk.Mapbox
import com.vlsu.maps.BuildConfig
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Mapbox.getInstance(this, BuildConfig.MAPBOX_TOKEN)
        RxJavaPlugins.setErrorHandler { throwable -> Timber.e(throwable) }
    }
}