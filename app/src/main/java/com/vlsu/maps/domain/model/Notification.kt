package com.vlsu.maps.domain.model

import java.util.*

class Notification {
    var id: Long = 0
    var type: NotificationType = NotificationType.UNDEFINED
    var time: Date? = null
    var description: String = ""
    var published: Boolean = false
}

