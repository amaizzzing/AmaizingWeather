package com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.city

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GeonameCity(
    @Expose val place_id: Long,
    @Expose val lat: Double,
    @Expose val lon: Double,
    @Expose val display_name: String,
    @Expose val address: Address
): Parcelable