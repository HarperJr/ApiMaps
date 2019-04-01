package com.vlsu.maps.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.vlsu.maps.data.database.entity.RegionEntity
import io.reactivex.Flowable

@Dao
interface RegionDao : BaseDao<RegionEntity> {

    @Query("SELECT * FROM Region")
    fun regions(): Flowable<List<RegionEntity>>

    @Query("SELECT * FROM Region ORDER BY `order`")
    fun getByOrder(): List<RegionEntity>

    @Query("SELECT * FROM Region WHERE name =  :name LIMIT 1")
    fun findByName(name: String): RegionEntity
}