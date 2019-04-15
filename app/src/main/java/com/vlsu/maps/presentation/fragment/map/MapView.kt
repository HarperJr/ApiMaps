package com.vlsu.maps.presentation.fragment.map

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.mapbox.mapboxsdk.geometry.LatLng
import com.vlsu.maps.domain.model.NotificationType

interface MapView : MvpView {

    fun setOriginBtnActive(active: Boolean)

    fun zoomIn()

    fun zoomOut()

    fun setOriginLocation(location: LatLng)

    fun setProgress(progress: Int)

    fun setProgressVisible(visible: Boolean)

    fun setNotification(notification: String, type: NotificationType)

    fun setBottomSheetExpanded(expanded: Boolean)
}