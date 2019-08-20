package com.vlsu.maps.presentation.fragment.map.item

import com.vlsu.maps.domain.model.NotificationType
import java.util.*

data class IncomingNotificationItem(
    val type: NotificationType,
    val date: Date? = null,
    val description: String,
    val unread: Boolean
)