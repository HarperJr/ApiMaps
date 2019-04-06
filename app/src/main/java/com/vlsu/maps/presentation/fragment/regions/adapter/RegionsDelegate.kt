package com.vlsu.maps.presentation.fragment.regions.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import com.vlsu.maps.R
import kotlinx.android.synthetic.main.region_view_item.view.*

class RegionsDelegate(
    private val onItemClickListener: (id: Long) -> Unit
) : AdapterDelegate<List<RegionItem>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return RegionItemView(
            LayoutInflater.from(parent.context).inflate(R.layout.region_view_item, parent, false)
        )
    }

    override fun isForViewType(items: List<RegionItem>, position: Int): Boolean = true

    override fun onBindViewHolder(
        items: List<RegionItem>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>
    ) {
        val item = items[position]
        with(holder.itemView) {
            region_view_name.text = item.name
            setOnClickListener { onItemClickListener.invoke(item.id) }
        }
    }

    class RegionItemView(item: View) : RecyclerView.ViewHolder(item)
}