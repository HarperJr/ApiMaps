package com.vlsu.maps.ui.app

import android.app.Application
import com.vlsu.maps.ui.dagger.AppComponent
import com.vlsu.maps.ui.dagger.module.AppModule
import com.vlsu.maps.ui.dagger.DaggerAppComponent

abstract class App : Application() {

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        private lateinit var component: AppComponent
        fun getComponent(): AppComponent = component
    }
}