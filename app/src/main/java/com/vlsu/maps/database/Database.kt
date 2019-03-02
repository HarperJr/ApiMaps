package com.vlsu.maps.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.vlsu.maps.BuildConfig
import com.vlsu.maps.database.dao.NotificationDao
import com.vlsu.maps.database.entity.NotificationEntity

@Database(
    version = BuildConfig.DATABASE_VERSION,
    exportSchema = false,
    entities = [
        NotificationEntity::class
    ]
)
abstract class Database : RoomDatabase() {

    abstract fun notificationDao(): NotificationDao
}