package com.vlsu.maps.presentation.fragment.routing_compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateFragment
import com.vlsu.maps.R
import com.vlsu.maps.di.Dagger
import kotlinx.android.synthetic.main.toolbar.*

class RoutingComposeFragment :
    MvpViewStateFragment<RoutingComposeView, RoutingComposePresenter, RoutingComposeViewState>(),
    RoutingComposeView {

    private val component = Dagger.appComponent.routingComposeComponent()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_routing_compose, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(toolbar) {
            setTitle(R.string.fragment_routing_compose_toolbar_title)
            setNavigationOnClickListener { presenter.onBackBtnClicked() }
        }
    }

    override fun createPresenter(): RoutingComposePresenter {
        return component.routingComposePresenter()
    }

    override fun createViewState(): RoutingComposeViewState {
        return component.routingComposeViewState()
    }

    override fun onNewViewStateInstance() {

    }

    companion object {
        fun newInstance() = RoutingComposeFragment()
    }
}