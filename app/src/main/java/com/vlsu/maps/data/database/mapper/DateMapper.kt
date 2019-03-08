package com.vlsu.maps.data.database.mapper

import org.mapstruct.Mapper
import java.util.*

@Mapper
abstract class DateMapper {

    fun entityToModel(entity: Long) = Date(entity)

    fun modelToEntity(model: Date) = model.time
}