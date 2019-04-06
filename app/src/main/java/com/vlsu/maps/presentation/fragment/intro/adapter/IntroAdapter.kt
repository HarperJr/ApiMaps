package com.vlsu.maps.presentation.fragment.intro.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.vlsu.maps.R
import javax.inject.Inject

class IntroAdapter @Inject constructor(
    context: Context) : PagerAdapter() {

    private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    var pages = listOf<IntroItem>()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val item = pages[position]
        val view = layoutInflater.inflate(R.layout.intro_view_item, container, false)
            .also { bindView(it, item) }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int = pages.count()

    private fun bindView(view: View, item: IntroItem) {
        view.findViewById<ImageView>(R.id.intro_image)
            .apply { setImageResource(item.imageRes) }
        view.findViewById<TextView>(R.id.intro_notation)
            .apply { setText(item.notationRes) }
    }
}