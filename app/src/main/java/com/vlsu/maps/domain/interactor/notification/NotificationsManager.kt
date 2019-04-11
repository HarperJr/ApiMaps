package com.vlsu.maps.domain.interactor.notification

import com.vlsu.maps.data.database.repository.NotificationRepository
import com.vlsu.maps.data.database.transaction.DbTransaction
import com.vlsu.maps.domain.model.Notification
import com.vlsu.maps.presentation.fragment.notifying.NotificationItem
import io.reactivex.Completable
import javax.inject.Inject

class NotificationsManager @Inject constructor(
    private val transaction: DbTransaction,
    private val notificationRepository: NotificationRepository
) {
    fun notify(notification: NotificationItem): Completable {
        return Completable
            .fromAction {
                //send to api
                transaction.runInTransaction {
                    notificationRepository.insert(mapToModel(notification))
                }
            }
            .doOnComplete {  }
            .doOnError {  }
    }

    private fun mapToModel(notification: NotificationItem): Notification {
        return Notification()
            .apply {
                type = notification.type
                time = notification.time
                description = notification.description
            }
    }
}