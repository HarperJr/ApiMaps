package com.vlsu.maps.dagger

import android.content.SharedPreferences
import com.vlsu.maps.dagger.module.AppModule
import com.vlsu.maps.dagger.module.NetworkModule
import com.vlsu.maps.dagger.subcomponent.InfoComponent
import com.vlsu.maps.dagger.subcomponent.MapComponent
import com.vlsu.maps.ui.activity.main.fragment.control.mvp.InfoPresenter
import com.vlsu.maps.ui.activity.main.fragment.map.mvp.MapPresenter
import com.vlsu.maps.ui.activity.main.fragment.map.mvp.MapViewState
import com.vlsu.maps.ui.activity.main.mvp.MainPresenter
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

    fun sharedPreferences(): SharedPreferences

    fun mainPresenter(): MainPresenter

    fun mapComponent(): MapComponent

    fun infoComponent(): InfoComponent

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        fun appModule(module: AppModule): Builder
    }
}