package com.vlsu.maps.domain.repository

import com.vlsu.maps.data.database.dao.NotificationDao
import com.vlsu.maps.data.database.entity.NotificationEntity
import com.vlsu.maps.data.database.mapper.NotificationMapper
import com.vlsu.maps.data.database.repository.NotificationRepository
import com.vlsu.maps.data.database.repository.base.RepositoryImpl
import com.vlsu.maps.domain.model.Notification
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationDao: NotificationDao,
    private val notificationMapper: NotificationMapper
) : RepositoryImpl<NotificationEntity, Notification, Long>(notificationDao, notificationMapper), NotificationRepository {

    override fun getWithBoundary(skip: Int, limit: Int): List<Notification> {
        return notificationMapper.entityListToModelList(notificationDao.getWithBoundary(skip, limit))
    }
}