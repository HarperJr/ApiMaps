package com.vlsu.maps.ui.activity.main.fragment.notification.mvp

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import javax.inject.Inject

class NotificationPresenter @Inject constructor(

) : MvpBasePresenter<NotificationView>() {

    override fun attachView(view: NotificationView?) {
        super.attachView(view)
    }

    override fun detachView(retainInstance: Boolean) {
        super.detachView(retainInstance)
    }
}
