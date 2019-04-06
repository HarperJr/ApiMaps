package com.vlsu.maps.domain.repository

import com.vlsu.maps.data.database.dao.RegionDao
import com.vlsu.maps.data.database.entity.RegionEntity
import com.vlsu.maps.data.database.mapper.RegionMapper
import com.vlsu.maps.data.database.repository.RegionRepository
import com.vlsu.maps.data.database.repository.base.RepositoryImpl
import com.vlsu.maps.domain.model.Region
import io.reactivex.Observable
import javax.inject.Inject

class RegionRepositoryImpl @Inject constructor(
    private val regionDao: RegionDao,
    private val regionMapper: RegionMapper
) : RepositoryImpl<RegionEntity, Region, Long>(regionDao, regionMapper), RegionRepository {

    override fun regions(): Observable<List<Region>> {
        return regionDao.regions()
            .map { regionMapper.entityListToModelList(it) }
            .toObservable()
    }

    override fun getByOrder(): List<Region> {
        return regionMapper.entityListToModelList(regionDao.getByOrder())
    }

    override fun findByName(name: String): Region {
        return regionMapper.entityToModel(regionDao.findByName(name))
    }

    override fun dropRegions() {
        regionDao.dropRegions()
    }
}