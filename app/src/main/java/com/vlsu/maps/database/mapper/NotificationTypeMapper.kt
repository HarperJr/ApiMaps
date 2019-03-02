package com.vlsu.maps.database.mapper

import com.vlsu.maps.model.NotificationType
import org.mapstruct.Mapper

@Mapper
abstract class NotificationTypeMapper {

    fun entityToModel(entity: Int) = NotificationType.of(entity)

    fun modelToEntity(model: NotificationType) = model.code
}
