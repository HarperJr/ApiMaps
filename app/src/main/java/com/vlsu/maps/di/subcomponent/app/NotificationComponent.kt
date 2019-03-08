package com.vlsu.maps.di.subcomponent.app

import com.vlsu.maps.presentation.fragment.notification.mvp.NotificationPresenter
import com.vlsu.maps.presentation.fragment.notification.mvp.NotificationViewState
import dagger.Subcomponent

@Subcomponent
interface NotificationComponent {

    fun notificationPresenter(): NotificationPresenter

    fun notificationViewState(): NotificationViewState
}