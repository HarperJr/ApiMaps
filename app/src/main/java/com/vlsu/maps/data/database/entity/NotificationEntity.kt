package com.vlsu.maps.data.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Notification")
class NotificationEntity {
    @PrimaryKey(autoGenerate = true) var id: Long = 0
    var type: Int = 0
    var time: Long = 0L
    var description: String = ""
    var published: Boolean = false
}
