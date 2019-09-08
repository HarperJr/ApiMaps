package com.vlsu.maps.domain.interactor.vehicles

import com.google.gson.Gson
import com.vlsu.maps.data.api.net.TcpTunnel
import com.vlsu.maps.data.api.net.adapter.TcpTunnelAdapter
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

class VehiclesProvider @Inject constructor(
    tcpTunnel: TcpTunnel,
    gson: Gson
) {
    private val tcpTunnel = TcpTunnelAdapter.json(tcpTunnel, gson)

    fun vehicles(): Observable<String> {
        return Observable
            .create<String> { subscriber ->
                tcpTunnel.subscribe(
                    VEHICLES_LISTENER, ::onConnected,
                    subscriber::onNext, subscriber::onError
                )
            }
            .doOnDispose { tcpTunnel.unsubscribe(VEHICLES_LISTENER) }
    }

    private fun onConnected() {
        Timber.d("Connected successfully")
        tcpTunnel.send("{ data: test }")
    }

    companion object {
        private const val VEHICLES_LISTENER = "VEHICLES_LISTENER"
    }
}