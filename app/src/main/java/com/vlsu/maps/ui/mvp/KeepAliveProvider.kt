package com.vlsu.maps.ui.mvp

interface KeepAliveProvider {
    fun keepAlive(parentKeepAlive: Boolean): Boolean
}