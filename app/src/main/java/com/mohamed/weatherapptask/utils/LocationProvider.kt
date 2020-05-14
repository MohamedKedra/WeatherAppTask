package com.mohamed.weatherapptask.utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.*

class LocationProvider(val context: Context) {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var location: MutableLiveData<Location> = MutableLiveData()

    companion object {

        fun getInstance(context: Context): LocationProvider {

            return LocationProvider(context)
        }
    }

    fun getCurrentLocation(): MutableLiveData<Location> {

        location.value = null

        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(context)

        if (checkPermissions()) {
            if (isLocationEnabled()) {
                fusedLocationClient.lastLocation.addOnCompleteListener { task ->
                    val loc = task.result
                    if (loc != null) {
                        location.value = loc
                    } else {
                        requestNewLocationData()
                    }
                }
                return location
            } else {
                Toast.makeText(context, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                context.startActivity(intent)
            }
        } else {
            Toast.makeText(context, "Error Location", Toast.LENGTH_LONG).show()
        }
        return location
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun requestNewLocationData() {
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 2000
        mLocationRequest.fastestInterval = 1000

        fusedLocationClient.requestLocationUpdates(
            mLocationRequest, getLocationCallback,
            Looper.myLooper()
        )
    }

    private val getLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            location.value = locationResult.lastLocation
        }
    }
}