package com.vlsu.maps.database.entity

import android.arch.persistence.room.Entity

@Entity(
    tableName = "Tile",
    primaryKeys = ["id"]
)
class TileEntity() {

    var id: Long = 0L

    var x: Int = 0

    var y: Int = 0

    var z: Int = 0

    var tileSourcePath: String = ""
}