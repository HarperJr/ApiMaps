package com.vlsu.maps.database.dao

import android.arch.persistence.room.Dao
import com.vlsu.maps.database.entity.NotificationEntity

@Dao
interface NotificationDao : BaseDao<NotificationEntity>
