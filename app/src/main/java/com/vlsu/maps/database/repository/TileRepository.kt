package com.vlsu.maps.database.repository

import com.vlsu.maps.model.Tile

interface TileRepository : BaseRepository<Tile, Long> {

    fun findByXYZ(x: Int, y: Int, z: Int): Tile
}