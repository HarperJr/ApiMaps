package com.vlsu.maps.data.api.net.adapter

import com.google.gson.Gson
import com.vlsu.maps.data.api.net.TcpTunnel

class TcpTunnelAdapterJson(
    private var tcpTunnel: TcpTunnel, private val gson: Gson
) : TcpTunnelAdapter<String> {

    override fun subscribe(
        key: String,
        onConnected: () -> Unit,
        onReceive: (String) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        tcpTunnel.subscribe(key, onConnected, { bytes ->
            val jsonString = String(bytes)
            onReceive.invoke(gson.toJson(jsonString))
        }, onError)
    }

    override fun unsubscribe(key: String) = tcpTunnel.unsubscribe(key)

    override fun send(data: String) = tcpTunnel.send(data.toByteArray())
}