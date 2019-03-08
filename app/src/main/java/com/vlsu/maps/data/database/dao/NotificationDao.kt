package com.vlsu.maps.data.database.dao

import android.arch.persistence.room.Dao
import com.vlsu.maps.data.database.entity.NotificationEntity

@Dao
interface NotificationDao : BaseDao<NotificationEntity>
