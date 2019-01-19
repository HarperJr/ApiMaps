package com.vlsu.maps.dagger.module

import com.vlsu.maps.database.Database
import com.vlsu.maps.database.mapper.TileMapper
import com.vlsu.maps.database.repository.TileRepository
import com.vlsu.maps.repository.TileRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideTileRepository(database: Database, tileMapper: TileMapper): TileRepository =
        TileRepositoryImpl(database.tileDao(), tileMapper)
}