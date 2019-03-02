package com.vlsu.maps.dagger

import android.content.SharedPreferences
import android.os.Handler
import com.vlsu.maps.dagger.module.AppModule
import com.vlsu.maps.dagger.module.NavigationModule
import com.vlsu.maps.dagger.module.NetworkModule
import com.vlsu.maps.dagger.subcomponent.*
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

    fun notificationComponent(): NotificationComponent

    fun infoComponent(): InfoComponent

    fun handler(): Handler

}