package com.vlsu.maps.repository

import com.vlsu.maps.database.dao.NotificationDao
import com.vlsu.maps.database.entity.NotificationEntity
import com.vlsu.maps.database.mapper.NotificationMapper
import com.vlsu.maps.database.repository.NotificationRepository
import com.vlsu.maps.database.repository.base.RepositoryImpl
import com.vlsu.maps.model.Notification
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationDao: NotificationDao,
    private val notificationMapper: NotificationMapper
) : RepositoryImpl<NotificationEntity, Notification, Long>(notificationDao, notificationMapper), NotificationRepository {

}