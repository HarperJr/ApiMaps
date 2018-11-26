package com.vlsu.maps.ui.mvp.core

import java.lang.IllegalStateException
import javax.inject.Inject

/**
 * @author Nikita Sychev
 **/
class PresenterCounter @Inject constructor() {

    private val connections = HashMap<String, Int>()

    fun incrementCounter(tag: String) {
        val currentCount = connections[tag] ?: 0
        connections[tag] = currentCount + 1
    }

    fun decrementCounter(tag: String): Boolean {
        val currentCount = connections[tag]
        when (currentCount) {
            null -> throw IllegalStateException("Invalid connections count")
            1 -> {
                connections.remove(tag)
                return true
            }
        }
        connections[tag] = currentCount!! - 1
        return false
    }
}