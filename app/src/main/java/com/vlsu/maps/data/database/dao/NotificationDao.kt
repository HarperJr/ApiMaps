package com.vlsu.maps.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.vlsu.maps.data.database.entity.NotificationEntity

@Dao
interface NotificationDao : BaseDao<NotificationEntity> {
    @Query("SELECT * FROM Notification WHERE id > :skip LIMIT :limit")
    fun getWithBoundary(skip: Int, limit: Int): List<NotificationEntity>
}
