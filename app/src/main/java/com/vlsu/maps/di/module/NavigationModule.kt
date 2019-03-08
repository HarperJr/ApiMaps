package com.vlsu.maps.di.module

import com.vlsu.maps.di.scope.AppScope
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router


@Module
class NavigationModule {

    @Provides
    @AppScope
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @AppScope
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    @AppScope
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.navigatorHolder
}