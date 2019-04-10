package com.vlsu.maps.presentation.fragment.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateFragment
import com.vlsu.maps.R
import com.vlsu.maps.di.Dagger
import com.vlsu.maps.presentation.fragment.notification.adapter.NotificationDelegate
import com.vlsu.maps.presentation.fragment.notification.adapter.NotificationItem
import kotlinx.android.synthetic.main.fragment_notification.*


class NotificationFragment : MvpViewStateFragment<NotificationView, NotificationPresenter, NotificationViewState>(),
    NotificationView {

    private val component = Dagger.appComponent.notificationComponent()
    private val notificationsAdapter by lazy {
        ListDelegationAdapter<List<NotificationItem>>(
            AdapterDelegatesManager<List<NotificationItem>>()
                .apply {
                    addDelegate(NotificationDelegate())
                })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(notifications_recycler) {
            adapter = notificationsAdapter
        }
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun setNotifications(notifications: List<NotificationItem>) {
        notificationsAdapter.items = notifications
        notificationsAdapter.notifyDataSetChanged()
    }

    override fun createPresenter(): NotificationPresenter {
        return component.notificationPresenter()
    }

    override fun createViewState(): NotificationViewState {
        return component.notificationViewState()
    }

    override fun onNewViewStateInstance() {

    }

    companion object {
        fun newInstance() = NotificationFragment()
    }
}
