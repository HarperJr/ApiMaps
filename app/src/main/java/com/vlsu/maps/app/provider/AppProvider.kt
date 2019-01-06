package com.vlsu.maps.app.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.vlsu.maps.dagger.Dagger
import com.vlsu.maps.dagger.DaggerAppComponent
import com.vlsu.maps.dagger.module.AppModule

class AppProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        Dagger.setComponent(DaggerAppComponent.builder()
            .appModule(AppModule(context!!))
            .build())
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