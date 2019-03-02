package com.vlsu.maps.dagger.module

import com.vlsu.maps.database.Database
import dagger.Module
import dagger.Provides

@Module
class DaoModule {

    @Provides
    fun provideNotificationDao(database: Database) = database.notificationDao()
}