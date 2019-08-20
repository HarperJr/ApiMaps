package com.vlsu.maps.domain.model

import com.vlsu.maps.R

enum class NotificationType(val code: Int) {
    UNDEFINED(0),
    SUPPORT_REQUEST(1),
    AMBULANCE(2),
    MESSAGE(3),
    COLLAPSE(4);

    companion object {
        fun of(code: Int): NotificationType = values().firstOrNull { it.code == code } ?: UNDEFINED

        fun getIcon(type: NotificationType) = when (type) {
            UNDEFINED -> R.drawable.ic_unknown
            MESSAGE -> R.drawable.ic_message
            SUPPORT_REQUEST -> R.drawable.ic_support
            AMBULANCE -> R.drawable.ic_ambulance
            COLLAPSE -> R.drawable.ic_collapse
        }
    }
}