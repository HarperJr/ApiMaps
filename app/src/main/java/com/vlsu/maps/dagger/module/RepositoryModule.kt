package com.vlsu.maps.dagger.module

import com.vlsu.maps.database.repository.NotificationRepository
import com.vlsu.maps.repository.NotificationRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindNotificationRepository(notificationRepositoryImpl: NotificationRepositoryImpl): NotificationRepository
}