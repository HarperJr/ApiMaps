package com.vlsu.maps.di.module

import com.vlsu.maps.data.database.Database
import dagger.Module
import dagger.Provides

@Module
class DaoModule {

    @Provides
    fun provideNotificationDao(database: Database) = database.notificationDao()
}