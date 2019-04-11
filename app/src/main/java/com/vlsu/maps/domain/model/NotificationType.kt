package com.vlsu.maps.domain.model

enum class NotificationType(val code: Int) {
    UNDEFINED(0),
    SUPPORT_REQUEST(1),
    WARNING(2),
    MESSAGE(3),
    COLLAPSE(4);

    companion object {
        fun of(code: Int): NotificationType = values().firstOrNull { it.code == code } ?: UNDEFINED
    }
}