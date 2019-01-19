package com.vlsu.maps.repository

import com.vlsu.maps.database.dao.TileDao
import com.vlsu.maps.database.entity.TileEntity
import com.vlsu.maps.database.mapper.TileMapper
import com.vlsu.maps.database.repository.RepositoryImpl
import com.vlsu.maps.database.repository.TileRepository
import com.vlsu.maps.model.Tile
import javax.inject.Inject

class TileRepositoryImpl(
    tileDao: TileDao,
    tileMapper: TileMapper
) : RepositoryImpl<TileEntity, Tile, Long>(), TileRepository {

    override val dao = tileDao
    override val mapper = tileMapper

    override fun findByXYZ(x: Int, y: Int, z: Int): Tile {
        return mapper.entityToModel(dao.findByXYZ(x, y, z))
    }
}