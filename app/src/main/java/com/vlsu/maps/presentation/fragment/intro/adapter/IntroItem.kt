package com.vlsu.maps.presentation.fragment.intro.adapter

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes

data class IntroItem(
    @DrawableRes val imageRes: Int,
    @StringRes val notationRes: Int
)
