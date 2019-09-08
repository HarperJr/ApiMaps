package com.vlsu.maps.data.api.net.adapter

import com.google.gson.Gson
import com.vlsu.maps.data.api.net.TcpTunnel

interface TcpTunnelAdapter<T> {
    fun subscribe(
        key: String,
        onConnected: () -> Unit,
        onReceive: (T) -> Unit,
        onError: (Throwable) -> Unit
    )

    fun unsubscribe(key: String)

    fun send(data: T)

    companion object {
        fun json(tunnel: TcpTunnel, gson: Gson) = TcpTunnelAdapterJson(tunnel, gson)

        fun xml(tunnel: TcpTunnel) {}
    }
}