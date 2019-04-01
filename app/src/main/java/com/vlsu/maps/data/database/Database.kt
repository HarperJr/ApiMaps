package com.vlsu.maps.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.vlsu.maps.BuildConfig
import com.vlsu.maps.data.database.dao.NotificationDao
import com.vlsu.maps.data.database.dao.RegionDao
import com.vlsu.maps.data.database.entity.NotificationEntity
import com.vlsu.maps.data.database.entity.RegionEntity

@Database(
    version = BuildConfig.DATABASE_VERSION,
    exportSchema = false,
    entities = [
        NotificationEntity::class,
        RegionEntity::class
    ]
)
abstract class Database : RoomDatabase() {

    abstract fun notificationDao(): NotificationDao

    abstract fun regionDao(): RegionDao
}