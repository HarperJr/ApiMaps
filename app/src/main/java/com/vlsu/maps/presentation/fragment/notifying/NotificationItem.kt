package com.vlsu.maps.presentation.fragment.notifying

import com.vlsu.maps.domain.model.NotificationType
import java.util.*

data class NotificationItem(
    var type: NotificationType,
    var time: Date,
    var description: String
)