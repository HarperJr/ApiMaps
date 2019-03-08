package com.vlsu.maps.domain.interactor

import android.content.Context
import java.io.BufferedInputStream
import java.io.IOException
import javax.inject.Inject

class AssetManager @Inject constructor(
    private val context: Context
) {
    @Throws(IOException::class)
    fun getFileContent(fileName: String): String {
        return with(context) {
            val inputStream = assets.open(fileName)
            with(BufferedInputStream(inputStream)) {
                bufferedReader(Charsets.UTF_8).readText()
            }
        }
    }
}