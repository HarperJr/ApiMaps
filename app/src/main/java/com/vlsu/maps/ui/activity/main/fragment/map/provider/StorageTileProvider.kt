package com.vlsu.maps.ui.activity.main.fragment.map.provider

import com.google.android.gms.maps.model.Tile
import com.google.android.gms.maps.model.TileProvider
import com.vlsu.maps.database.repository.TileRepository
import com.vlsu.maps.database.transaction.DbTransaction
import javax.inject.Inject

class StorageTileProvider @Inject constructor(
    private val tileRepository: TileRepository
) : TileProvider {

    override fun getTile(x: Int, y: Int, z: Int): Tile {
        return loadTile(tileRepository.findByXYZ(x, y, z).tileSourcePath)
    }

    private fun loadTile(tileSourcePath: String): Tile {

        return Tile(0, 0, null)
    }
}