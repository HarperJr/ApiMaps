package com.vlsu.maps.database.dao

import android.arch.persistence.db.SupportSQLiteQuery
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.RawQuery
import android.arch.persistence.room.Update

interface BaseDao<Entity> {

    @Insert
    fun insert(entity: Entity)

    @Insert
    fun insert(entities: List<Entity>)

    @Update
    fun update(entity: Entity)

    @Delete
    fun delete(entity: Entity)

    @Delete
    fun delete(entities: List<Entity>)

    @RawQuery
    fun getSingle(query: SupportSQLiteQuery): Entity

    @RawQuery
    fun getList(query: SupportSQLiteQuery): List<Entity>
}