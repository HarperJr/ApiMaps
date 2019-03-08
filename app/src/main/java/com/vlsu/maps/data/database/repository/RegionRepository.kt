package com.vlsu.maps.data.database.repository

import com.vlsu.maps.data.database.repository.base.Repository
import com.vlsu.maps.domain.model.Region
import io.reactivex.Observable

interface RegionRepository : Repository<Region, Long> {
    fun regions(): Observable<List<Region>>

    fun getByOrder(): List<Region>

    fun findByName(name: String): Region
}