package com.vlsu.maps.ui.activity.main.fragment.notification.adapter

import com.vlsu.maps.model.NotificationType
import java.util.*

data class NotificationItem(
    var type: NotificationType,
    var time: Date? = null,
    var description: String,
    var published: Boolean
)