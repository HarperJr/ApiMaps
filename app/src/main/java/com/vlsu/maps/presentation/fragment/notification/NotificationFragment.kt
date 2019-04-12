package com.vlsu.maps.presentation.fragment.notification

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
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
import com.vlsu.maps.presentation.view.visibility
import kotlinx.android.synthetic.main.fragment_notification.*


class NotificationFragment : MvpViewStateFragment<NotificationView, NotificationPresenter, NotificationViewState>(),
    NotificationView {

    private val component = Dagger.appComponent.notificationComponent()
    private val adapter by lazy {
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

        notifications_recycler.adapter = adapter
        notifications_recycler.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        notification_add_btn.setOnClickListener { presenter.onAddBtnClicked() }
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun setNotifications(notifications: List<NotificationItem>) {
        notification_no_data_label.visibility(notifications.isEmpty())
        adapter.items = notifications
        adapter.notifyDataSetChanged()
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
