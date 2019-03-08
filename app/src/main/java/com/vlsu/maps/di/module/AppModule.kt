package com.vlsu.maps.di.module

import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import com.vlsu.maps.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context, private val handler: Handler) {

    @Provides
    @AppScope
    fun provideContext() = context

    @Provides
    @AppScope
    fun provideHandler() = handler

    @Provides
    @AppScope
    fun provideSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences(DATA_STORE, Context.MODE_PRIVATE)
    }

    companion object {
        private const val DATA_STORE = "DATA_STORE"
    }
}