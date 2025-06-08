package com.example.myweather.data.location


import android.content.Context
import androidx.core.content.ContextCompat
import com.example.myweather.domain.location.LocationProvider
import com.example.myweather.domain.model.entity.location.CurrentLocation
import com.google.android.gms.location.FusedLocationProviderClient
import android.Manifest
import android.content.pm.PackageManager
import com.example.myweather.data.mappers.toAppLocation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GoogleLocationService(
    private val locationClient: FusedLocationProviderClient,
    private val context: Context,
): LocationProvider {

    override suspend fun getCurrentLocation(): CurrentLocation? {
        return suspendCoroutine { con ->
            val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
            val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

            if(!hasAccessCoarseLocationPermission || !hasAccessFineLocationPermission ) {
                con.resume(null)
                        return@suspendCoroutine
            }

            locationClient.lastLocation
                .addOnSuccessListener {
                    con.resume(it.toAppLocation())
                }
                .addOnFailureListener {
                    con.resume(null)
                }
        }
    }
}