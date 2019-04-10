package com.vlsu.maps.data.database.repository

import com.vlsu.maps.data.database.repository.base.Repository
import com.vlsu.maps.domain.model.Notification

interface NotificationRepository : Repository<Notification, Long> {
    fun getWithBoundary(skip: Int, limit: Int): List<Notification>
}