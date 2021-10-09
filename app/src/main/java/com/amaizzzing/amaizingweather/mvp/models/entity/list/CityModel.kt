package com.amaizzzing.amaizingweather.mvp.models.entity.list

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityModel(
    val cityId: Long,
    val lat: Double,
    val lon: Double,
    val cityName: String,
    val fullCityName: String
): Parcelable