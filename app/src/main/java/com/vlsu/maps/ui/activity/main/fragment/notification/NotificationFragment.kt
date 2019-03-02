package com.vlsu.maps.ui.activity.main.fragment.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import com.hannesdorfmann.mosby.mvp.viewstate.MvpViewStateFragment
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState
import com.vlsu.maps.R
import com.vlsu.maps.dagger.Dagger
import com.vlsu.maps.ui.activity.main.fragment.notification.adapter.NotificationDelegate
import com.vlsu.maps.ui.activity.main.fragment.notification.adapter.NotificationItem
import com.vlsu.maps.ui.activity.main.fragment.notification.mvp.NotificationPresenter
import com.vlsu.maps.ui.activity.main.fragment.notification.mvp.NotificationView
import kotlinx.android.synthetic.main.fragment_notification.*


class NotificationFragment : MvpViewStateFragment<NotificationView, NotificationPresenter>(),
    NotificationView {

    private val component = Dagger.getComponent().notificationComponent()
    private val notificationsAdapter by lazy {
        ListDelegationAdapter<List<NotificationItem>>(
            AdapterDelegatesManager<List<NotificationItem>>()
                .apply {
                    addDelegate(NotificationDelegate())
                })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(notificationsRecycler) {
            adapter = notificationsAdapter
        }
    }

    override fun createPresenter(): NotificationPresenter {
        return component.notificationPresenter()
    }

    override fun createViewState(): ViewState<*> {
        return component.notificationViewState()
    }

    override fun onNewViewStateInstance() {

    }

    companion object {
        fun newInstance() = NotificationFragment()
    }
}
