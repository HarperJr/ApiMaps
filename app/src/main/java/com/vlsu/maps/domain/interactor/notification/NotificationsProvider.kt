package com.vlsu.maps.domain.interactor.notification

import com.vlsu.maps.data.database.repository.NotificationRepository
import com.vlsu.maps.domain.model.Notification
import com.vlsu.maps.presentation.fragment.notification.adapter.NotificationItem
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class NotificationsProvider @Inject constructor(
  private val notificationRepository: NotificationRepository
) {
    fun notifications(): Observable<List<NotificationItem>> = Single
        .fromCallable {
            notificationRepository.getWithBoundary(0, 20)
        }
        .map { mapToItem(it) }
        .toObservable()

    private fun mapToItem(notifications: List<Notification>): List<NotificationItem> {
        return notifications.map {
            NotificationItem(
                type = it.type,
                time = it.time,
                description = it.description,
                published = it.published
            )
        }
    }

}