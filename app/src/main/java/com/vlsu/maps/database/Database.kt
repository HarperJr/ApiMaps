package com.vlsu.maps.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.vlsu.maps.BuildConfig

@Database(
    version = BuildConfig.DATABASE_VERSION, entities = [
    ]
)
abstract class Database : RoomDatabase() {

}