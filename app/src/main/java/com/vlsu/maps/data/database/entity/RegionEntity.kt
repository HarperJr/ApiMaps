package com.vlsu.maps.data.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Region")
class RegionEntity {
    @PrimaryKey(autoGenerate = false)
    var id = 0L
    var name = ""
    var code = ""
    var order = ""
    var north: Double = 0.0
    var south: Double = 0.0
    var east: Double = 0.0
    var west: Double = 0.0
}