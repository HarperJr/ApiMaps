package com.vlsu.maps.dagger.module

import com.vlsu.maps.database.mapper.NotificationMapper
import dagger.Module
import dagger.Provides
import dagger.Reusable
import org.mapstruct.factory.Mappers

@Module
class MapperModule {

    @Provides
    @Reusable
    fun provideNotificationMapper() = Mappers.getMapper(NotificationMapper::class.java)!!
}