package com.vlsu.maps.domain.model

import java.util.*

data class IncomingNotification(
    val type: NotificationType,
    val date: Date? = null,
    val description: String
)