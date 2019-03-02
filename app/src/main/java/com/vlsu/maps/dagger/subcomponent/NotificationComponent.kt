package com.vlsu.maps.dagger.subcomponent

import com.vlsu.maps.ui.activity.main.fragment.notification.mvp.NotificationPresenter
import com.vlsu.maps.ui.activity.main.fragment.notification.mvp.NotificationViewState
import dagger.Subcomponent

@Subcomponent
interface NotificationComponent {

    fun notificationPresenter(): NotificationPresenter

    fun notificationViewState(): NotificationViewState
}