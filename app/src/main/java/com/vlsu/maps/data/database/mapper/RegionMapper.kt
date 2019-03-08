package com.vlsu.maps.data.database.mapper

import com.vlsu.maps.data.database.entity.RegionEntity
import com.vlsu.maps.domain.model.Region
import org.mapstruct.Mapper

@Mapper
interface RegionMapper : BaseMapper<Region, RegionEntity>