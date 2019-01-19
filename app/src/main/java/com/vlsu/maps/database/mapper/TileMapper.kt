package com.vlsu.maps.database.mapper

import com.vlsu.maps.database.entity.TileEntity
import com.vlsu.maps.model.Tile
import org.mapstruct.Mapper

@Mapper
interface TileMapper: BaseMapper<Tile, TileEntity>