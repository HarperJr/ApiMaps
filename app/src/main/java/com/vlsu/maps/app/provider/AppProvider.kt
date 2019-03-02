package com.vlsu.maps.app.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.Handler
import com.mapbox.mapboxsdk.Mapbox
import com.vlsu.maps.BuildConfig
import com.vlsu.maps.dagger.Dagger
import com.vlsu.maps.dagger.DaggerAppComponent
import com.vlsu.maps.dagger.module.AppModule
import com.vlsu.maps.dagger.module.DatabaseModule
import com.vlsu.maps.dagger.subcomponent.DaggerDatabaseComponent
import timber.log.Timber

class AppProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        /**Database component**/
        val databaseComponent = DaggerDatabaseComponent.builder()
            .databaseModule(DatabaseModule(context!!))
            .build()
        /**Application component**/
        val appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(context!!, Handler()))
            .databaseComponent(databaseComponent)
            .build()
        Dagger.setComponent(appComponent)
        Timber.plant(Timber.DebugTree())
        Mapbox.getInstance(context!!, BuildConfig.MAPBOX_TOKEN)
        return false
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        return null
    }

    override fun query(p0: Uri, p1: Array<String>?, p2: String?, p3: Array<String>?, p4: String?): Cursor? {
        return null
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<String>?): Int {
        return 0
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<String>?): Int {
        return 0
    }

    override fun getType(p0: Uri): String? {
        return null
    }
}