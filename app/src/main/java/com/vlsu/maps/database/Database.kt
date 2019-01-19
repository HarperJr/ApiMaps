package com.vlsu.maps.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.vlsu.maps.BuildConfig
import com.vlsu.maps.database.dao.TileDao
import com.vlsu.maps.database.entity.TileEntity

@Database(
    version = BuildConfig.DATABASE_VERSION, entities = [
        TileEntity::class
    ]
)
abstract class Database : RoomDatabase() {

    abstract fun tileDao(): TileDao

}