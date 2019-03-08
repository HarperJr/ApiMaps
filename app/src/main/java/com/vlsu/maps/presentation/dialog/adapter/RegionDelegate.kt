package com.vlsu.maps.presentation.fragment.notification.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import com.vlsu.maps.R

class RegionDelegate : AdapterDelegate<List<RegionItem>>() {

    var onItemClickListener: ((id: Long) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NotificationItemView(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.region_view_item, parent, false)
        )
    }

    override fun isForViewType(items: List<RegionItem>, position: Int): Boolean = true

    override fun onBindViewHolder(
        items: List<RegionItem>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>
    ) {
        val item = items[position]
        with(holder.itemView) {
            setOnClickListener { onItemClickListener?.invoke(item.id) }
        }
    }

    class NotificationItemView(item: View) : RecyclerView.ViewHolder(item)
}