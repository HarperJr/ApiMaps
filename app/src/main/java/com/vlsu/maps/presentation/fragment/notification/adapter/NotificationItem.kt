package com.vlsu.maps.presentation.fragment.notification.adapter

import com.vlsu.maps.domain.model.NotificationType
import java.util.*

data class NotificationItem(
    var type: NotificationType,
    var time: Date? = null,
    var description: String,
    var published: Boolean
)