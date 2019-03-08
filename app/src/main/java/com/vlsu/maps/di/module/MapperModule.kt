package com.vlsu.maps.di.module

import com.vlsu.maps.data.database.mapper.NotificationMapper
import com.vlsu.maps.data.database.mapper.RegionMapper
import dagger.Module
import dagger.Provides
import dagger.Reusable
import org.mapstruct.factory.Mappers

@Module
class MapperModule {

    @Provides
    @Reusable
    fun provideNotificationMapper() = Mappers.getMapper(NotificationMapper::class.java)!!

    @Provides
    @Reusable
    fun provideRegionMapper() = Mappers.getMapper(RegionMapper::class.java)!!
}