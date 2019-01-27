package com.vlsu.maps.dagger.module

import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class AppModule(private val context: Context, private val handler: Handler) {

    @Provides
    fun provideContext() = context

    @Provides
    fun provideHandler() = handler

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences(DATA_STORE, Context.MODE_PRIVATE)
    }

    companion object {
        private const val DATA_STORE = "DATA_STORE"
    }
}