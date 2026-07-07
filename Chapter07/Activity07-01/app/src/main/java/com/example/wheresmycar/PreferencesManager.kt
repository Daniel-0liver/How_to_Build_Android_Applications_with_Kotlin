package com.example.wheresmycar

import android.content.Context
import com.google.android.gms.maps.model.LatLng

class PreferencesManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("wheres_my_car_prefs", Context.MODE_PRIVATE)

    fun saveLocation(latLng: LatLng) {
        sharedPreferences.edit()
            .putFloat("lat", latLng.latitude.toFloat())
            .putFloat("lng", latLng.longitude.toFloat())
            .apply()
    }

    fun loadLocation(): LatLng? {
        val lat = sharedPreferences.getFloat("lat", Float.NaN)
        val lng = sharedPreferences.getFloat("lng", Float.NaN)
        return if (lat.isNaN() || lng.isNaN()) null else LatLng(lat.toDouble(), lng.toDouble())
    }
}