package com.vlsu.maps.presentation.fragment.intro

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vlsu.maps.R
import com.vlsu.maps.di.Dagger
import com.vlsu.maps.presentation.fragment.intro.adapter.IntroAdapter
import com.vlsu.maps.presentation.fragment.intro.adapter.IntroItem
import com.vlsu.maps.presentation.fragment.map.MapScreen
import kotlinx.android.synthetic.main.fragment_intro.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class IntroFragment : Fragment() {

    private val component = Dagger.appComponent.introComponent()
    @Inject
    lateinit var adapter: IntroAdapter
    @Inject
    lateinit var router: Router

    private val introPages = listOf(
        IntroItem(R.drawable.ic_notifications, R.string.intro_about),
        IntroItem(R.drawable.ic_routing, R.string.intro_settings),
        IntroItem(R.drawable.ic_settings, R.string.intro_routes),
        IntroItem(R.drawable.ic_unknown, R.string.intro_offline)
    )
    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        currentPage = savedInstanceState?.getInt(ARG_CURRENT_PAGE) ?: 0
        adapter.pages = introPages
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_intro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        intro_pager.apply {
            adapter = this@IntroFragment.adapter
            setCurrentItem(currentPage, false)
            addOnPageChangeListener(onPageChangeListener)
        }
        intro_continue_btn.apply {
            setOnClickListener { onContinueBtnClicked() }
        }
    }

    override fun onDestroyView() {
        intro_pager.removeOnPageChangeListener(onPageChangeListener)
        super.onDestroyView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("ARG_CURRENT_PAGE", currentPage)
    }

    private fun onContinueBtnClicked() {
        router.newRootScreen(MapScreen())
    }

    private val onPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(p0: Int) {

        }

        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
        }

        override fun onPageSelected(position: Int) {
            currentPage = position
        }
    }

    companion object {
        fun newInstance() = IntroFragment()

        private const val ARG_CURRENT_PAGE = "ARG_CURRENT_PAGE"
    }
}