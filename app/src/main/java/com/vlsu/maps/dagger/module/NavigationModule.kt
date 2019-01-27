package com.vlsu.maps.dagger.module

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router


@Module
class NavigationModule {

    @Provides
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.navigatorHolder
}