package com.vlsu.maps.presentation.fragment.notifying

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.vlsu.maps.domain.model.NotificationType

interface NotifyingView : MvpView {
    fun setNotificationType(notificationType: NotificationType)
}