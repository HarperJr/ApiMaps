package com.vlsu.maps.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.vlsu.maps.database.entity.TileEntity

@Dao
interface TileDao : BaseDao<TileEntity> {

    @Query("SELECT * FROM Tile WHERE x = :x AND y = :y AND z = :z")
    fun findByXYZ(x: Int, y: Int, z: Int): TileEntity
}