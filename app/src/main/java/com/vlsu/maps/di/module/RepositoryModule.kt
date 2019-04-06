package com.vlsu.maps.di.module

import com.vlsu.maps.data.database.repository.RegionRepository
import com.vlsu.maps.domain.repository.RegionRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindRegionsRepository(regionRepository: RegionRepositoryImpl): RegionRepository
}