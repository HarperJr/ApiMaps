package com.vlsu.maps.presentation.fragment.notification.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import com.vlsu.maps.R

class NotificationDelegate : AdapterDelegate<List<NotificationItem>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NotificationHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.notification_view_item, parent, false)
        )
    }

    override fun isForViewType(items: List<NotificationItem>, position: Int): Boolean = true

    override fun onBindViewHolder(
        items: List<NotificationItem>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>
    ) {
        (holder as NotificationHolder).bind(items[position])
    }

    class NotificationHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.notification_view_item_title)

        fun bind(item: NotificationItem) {
            title.text = item.description
        }
    }
}