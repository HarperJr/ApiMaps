package com.vlsu.maps.ui.handler

import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class LocationUpdatesHandler @Inject constructor(context: Context) {

    private val updatesSubject = PublishSubject.create<Location>()
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private val locationRequest = LocationRequest().apply {
        interval = 900000
        fastestInterval = 120000
    }

    @Throws(SecurityException::class)
    fun startUpdates() {
        fusedLocationClient
            .requestLocationUpdates(locationRequest, locationCallback, null)
    }

    fun stopUpdates() {
        fusedLocationClient
            .removeLocationUpdates(locationCallback)
    }

    fun updates() = updatesSubject

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            if (locationResult == null) {
                updatesSubject.onError(Exception("Location update is unavailable"))
            } else {
                for (location in locationResult.locations) {
                    updatesSubject.onNext(location)
                }
            }
        }
    }
}