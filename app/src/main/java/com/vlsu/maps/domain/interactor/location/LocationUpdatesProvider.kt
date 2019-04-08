package com.vlsu.maps.domain.interactor.location

import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LocationUpdatesProvider @Inject constructor(context: Context) {

    private val updatesSubject = PublishSubject.create<Location>()
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private val locationRequest = LocationRequest().apply {
        interval = TimeUnit.SECONDS.toMillis(20)
        maxWaitTime = TimeUnit.SECONDS.toMillis(60)
        fastestInterval = TimeUnit.SECONDS.toMillis(10)
    }
    private var requestingUpdates = false

    @Throws(SecurityException::class)
    fun startUpdates() {
        if (requestingUpdates) return
        requestingUpdates = true

        fusedLocationClient
            .requestLocationUpdates(locationRequest, locationCallback, null)
        fusedLocationClient.lastLocation
            .addOnCompleteListener {
                val lastLocation = it.result
                if (lastLocation != null) updatesSubject.onNext(lastLocation)
            }
    }

    fun stopUpdates() {
        if (!requestingUpdates) return
        requestingUpdates = false

        fusedLocationClient
            .removeLocationUpdates(locationCallback)
    }

    fun updates() = updatesSubject

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            if (locationResult != null) {
                updatesSubject.onNext(locationResult.lastLocation)
            }
        }
    }
}