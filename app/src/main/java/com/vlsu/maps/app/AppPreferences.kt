package com.vlsu.maps.app

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferences @Inject constructor(
    private val preferences: SharedPreferences
) {
    fun region(): String = preferences.getString(PREFS_REGION, "")!!

    companion object {
        private const val PREFS_REGION = "PREFS_REGION"
    }
}