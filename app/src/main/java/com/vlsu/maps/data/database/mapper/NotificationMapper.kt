package com.vlsu.maps.data.database.mapper

import com.vlsu.maps.data.database.entity.NotificationEntity
import com.vlsu.maps.domain.model.Notification
import org.mapstruct.Mapper

@Mapper(
    uses = [
        DateMapper::class,
        NotificationTypeMapper::class
    ]
)
interface NotificationMapper : BaseMapper<Notification, NotificationEntity>