package com.vlsu.maps.presentation.fragment.map.interactor

import android.content.Context
import com.mapbox.mapboxsdk.geometry.LatLngBounds
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.offline.*
import com.vlsu.maps.domain.model.Region
import io.reactivex.subjects.BehaviorSubject
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject

class MapRegionLoader @Inject constructor(
    private val context: Context
) {

    private val offlineManager = OfflineManager.getInstance(context)
    private val downloadStatusSubject = BehaviorSubject.create<Float>()

    fun isRegionLoaded(region: Region, callback: (Boolean) -> Unit) {
        offlineManager.listOfflineRegions(object : OfflineManager.ListOfflineRegionsCallback {
            override fun onList(offlineRegions: Array<out OfflineRegion>?) {
                if (offlineRegions == null) {
                    callback.invoke(false)
                } else {
                    offlineRegions
                        .find {
                            try {
                                val json = JSONObject(it.metadata.toString(charsetEncoding))
                                json.getString(JSON_FIELD_CODE) == region.code
                            } catch (ex: Exception) {
                                false
                            }
                        }
                        .also {
                            callback.invoke(it != null)
                        }
                }
            }

            override fun onError(error: String?) {
                callback.invoke(false)
            }
        })
    }

    fun loadRegion(region: Region) {
        val latlngBounds = LatLngBounds.from(region.north, region.east, region.south, region.west)
        val definition = OfflineTilePyramidRegionDefinition(
            Style.MAPBOX_STREETS,
            latlngBounds,
            Constants.MIN_ZOOM,
            Constants.MAX_ZOOM,
            context.resources.displayMetrics.density
        )
        var metadata: ByteArray = byteArrayOf()
        try {
            val json = JSONObject()
                .apply { put(JSON_FIELD_CODE, region.code) }
                .toString()
            metadata = json.toByteArray(Charsets.UTF_8)
        } catch (ex: Exception) {

        }
        offlineManager.createOfflineRegion(definition, metadata, offlineRegionCallback)
    }

    private val offlineRegionCallback = object : OfflineManager.CreateOfflineRegionCallback {
        override fun onCreate(offlineRegion: OfflineRegion) {
            with(offlineRegion) {
                setDownloadState(OfflineRegion.STATE_ACTIVE)
                setObserver(object : OfflineRegion.OfflineRegionObserver {
                    override fun mapboxTileCountLimitExceeded(limit: Long) {
                        Timber.d("mapboxTileCountLimitExceeded $limit")
                    }

                    override fun onStatusChanged(status: OfflineRegionStatus) {
                        if (status.isComplete) {
                            downloadStatusSubject.onComplete()
                        } else if (status.isRequiredResourceCountPrecise) {
                            val percentage = if (status.requiredResourceCount >= 0)
                                status.completedResourceCount.toFloat() / status.requiredResourceCount.toFloat() * 100.0 else 0.0
                            downloadStatusSubject.onNext(percentage.toFloat())
                        }
                    }

                    override fun onError(error: OfflineRegionError) {
                        downloadStatusSubject.onError(Exception(error.message))
                    }
                })
            }
        }

        override fun onError(error: String?) {
            downloadStatusSubject.onError(java.lang.Exception(error))
        }
    }

    companion object {
        private const val JSON_FIELD_CODE = "JSON_FIELD_CODE"
        private val charsetEncoding = Charsets.UTF_8
    }
}