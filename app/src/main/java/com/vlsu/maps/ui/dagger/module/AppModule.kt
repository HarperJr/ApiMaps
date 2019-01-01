package com.vlsu.maps.ui.dagger.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences(DATA_STORE, Context.MODE_PRIVATE)
    }

    companion object {
        private const val DATA_STORE = "DATA_STORE"
    }
}