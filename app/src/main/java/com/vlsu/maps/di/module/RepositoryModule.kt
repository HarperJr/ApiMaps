package com.vlsu.maps.di.module

import com.vlsu.maps.data.database.repository.NotificationRepository
import com.vlsu.maps.domain.repository.NotificationRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindNotificationRepository(notificationRepositoryImpl: NotificationRepositoryImpl): NotificationRepository
}