package com.vlsu.maps.presentation.fragment.routing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateFragment
import com.vlsu.maps.R
import com.vlsu.maps.di.Dagger
import kotlinx.android.synthetic.main.fragment_routing.*

class RoutingFragment : MvpViewStateFragment<RoutingView, RoutingPresenter, RoutingViewState>(), RoutingView {

    private val component = Dagger.appComponent.routingComponent()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_routing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_routing_compose_btn.setOnClickListener { presenter.onComposeBtnClicked() }
    }

    override fun createPresenter(): RoutingPresenter {
        return component.routingPresenter()
    }

    override fun createViewState(): RoutingViewState {
        return component.routingViewState()
    }

    override fun onNewViewStateInstance() {

    }

    companion object {
        fun newInstance() = RoutingFragment()
    }
}