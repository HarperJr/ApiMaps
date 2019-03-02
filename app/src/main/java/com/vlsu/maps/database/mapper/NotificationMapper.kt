package com.vlsu.maps.database.mapper

import com.vlsu.maps.database.entity.NotificationEntity
import com.vlsu.maps.model.Notification
import org.mapstruct.Mapper

@Mapper(
    uses = [
        DateMapper::class,
        NotificationTypeMapper::class
    ]
)
interface NotificationMapper : BaseMapper<Notification, NotificationEntity>