package com.vlsu.maps.app

import android.app.Application
import com.vlsu.maps.dagger.AppComponent
import com.vlsu.maps.dagger.module.AppModule
import com.vlsu.maps.dagger.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}