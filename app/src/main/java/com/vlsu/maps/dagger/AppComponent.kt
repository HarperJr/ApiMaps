package com.vlsu.maps.dagger

import android.content.SharedPreferences
import com.vlsu.maps.dagger.module.AppModule
import com.vlsu.maps.dagger.module.NetworkModule
import com.vlsu.maps.ui.mvp.MvpDelegate
import com.vlsu.maps.ui.mvp.core.MvpProcessor
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    fun getMvpProcessor(): MvpProcessor

    fun getSharedPreferences(): SharedPreferences

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        fun appModule(module: AppModule): Builder
    }
}