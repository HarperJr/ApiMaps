package com.vlsu.maps.di.subcomponent.app

import com.vlsu.maps.presentation.fragment.notification.NotificationPresenter
import com.vlsu.maps.presentation.fragment.notification.NotificationViewState
import dagger.Subcomponent

@Subcomponent
interface NotificationComponent {

    fun notificationPresenter(): NotificationPresenter

    fun notificationViewState(): NotificationViewState
}