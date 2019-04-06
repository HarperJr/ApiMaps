package com.vlsu.maps.di

import android.content.SharedPreferences
import android.os.Handler
import com.vlsu.maps.di.module.AppModule
import com.vlsu.maps.di.module.NavigationModule
import com.vlsu.maps.di.module.NetworkModule
import com.vlsu.maps.di.scope.AppScope
import com.vlsu.maps.di.subcomponent.app.*
import dagger.Component

@AppScope
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

    fun introComponent(): IntroComponent

    fun mainComponent(): MainComponent

    fun mapComponent(): MapComponent

    fun notificationComponent(): NotificationComponent

    fun settingsComponent(): SettingsComponent

    fun handler(): Handler
}