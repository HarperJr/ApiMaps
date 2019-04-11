package com.vlsu.maps.presentation.fragment.notification

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.vlsu.maps.presentation.fragment.notification.adapter.NotificationItem

interface NotificationView : MvpView {
    fun setNotifications(notifications: List<NotificationItem>)
}
