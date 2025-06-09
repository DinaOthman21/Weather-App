package com.example.myweather.data.location

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.myweather.domain.location.LocationProvider
import com.example.myweather.domain.model.entity.location.CurrentLocation
import com.google.android.gms.location.FusedLocationProviderClient
import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationManager
import com.example.myweather.data.mappers.toAppLocation
import com.google.android.gms.location.Priority
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class GoogleLocationService(
    private val locationClient: FusedLocationProviderClient,
    private val context: Context,
) : LocationProvider {

    override suspend fun getCurrentLocation(): CurrentLocation? {

        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED


        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!hasAccessCoarseLocationPermission || !hasAccessFineLocationPermission || (!isGpsEnabled && !isNetworkEnabled)) {
            return null
        }

        return suspendCancellableCoroutine { cont ->
            locationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                null
            ).addOnSuccessListener { location ->
                cont.resume(location.toAppLocation())
            }.addOnFailureListener {
                cont.resume(null)
            }.addOnCanceledListener {
                cont.cancel()
            }
        }
    }
}