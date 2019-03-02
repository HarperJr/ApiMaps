package com.vlsu.maps.app

import android.app.Application
import com.mapbox.mapboxsdk.Mapbox
import com.vlsu.maps.BuildConfig

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Mapbox.getInstance(this, BuildConfig.MAPBOX_TOKEN)
    }
}