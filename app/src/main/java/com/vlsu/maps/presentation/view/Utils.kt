package com.vlsu.maps.presentation.view

import android.view.View

fun View.visibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}