package com.vlsu.maps.ui.dagger

import android.content.SharedPreferences
import com.vlsu.maps.ui.dagger.module.AppModule
import com.vlsu.maps.ui.dagger.module.NetworkModule
import com.vlsu.maps.ui.mvp.MvpDelegate
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

    fun getMvpDelegate(): MvpDelegate

    fun getSharedPreferences(): SharedPreferences

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        fun appModule(module: AppModule): Builder
    }
}