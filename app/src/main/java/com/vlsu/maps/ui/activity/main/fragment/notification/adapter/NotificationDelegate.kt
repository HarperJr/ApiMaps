package com.vlsu.maps.ui.activity.main.fragment.notification.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import com.vlsu.maps.R

class NotificationDelegate : AdapterDelegate<List<NotificationItem>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NotificationItemView(
            LayoutInflater.from(parent.context).inflate(R.layout.notification_view_item, parent, false)
        )
    }

    override fun isForViewType(items: List<NotificationItem>, position: Int): Boolean = true

    override fun onBindViewHolder(
        items: List<NotificationItem>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>
    ) {
        val item = items[position]
        with(holder.itemView) {

        }
    }

    class NotificationItemView(item: View) : RecyclerView.ViewHolder(item)
}