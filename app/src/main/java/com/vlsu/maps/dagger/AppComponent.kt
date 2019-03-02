package com.vlsu.maps.dagger

import android.content.SharedPreferences
import android.os.Handler
import com.vlsu.maps.dagger.module.AppModule
import com.vlsu.maps.dagger.module.NetworkModule
import com.vlsu.maps.dagger.module.NavigationModule
import com.vlsu.maps.dagger.subcomponent.DatabaseComponent
import com.vlsu.maps.dagger.subcomponent.InfoComponent
import com.vlsu.maps.dagger.subcomponent.MainComponent
import com.vlsu.maps.dagger.subcomponent.MapComponent
import com.vlsu.maps.ui.activity.main.mvp.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        NavigationModule::class
    ],
    dependencies = [
        DatabaseComponent::class
    ]
)
interface AppComponent {

    fun sharedPreferences(): SharedPreferences

    fun mainComponent(): MainComponent

    fun mapComponent(): MapComponent

    fun infoComponent(): InfoComponent

    fun handler(): Handler

}