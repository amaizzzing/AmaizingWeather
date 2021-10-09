package com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.city

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
    @Expose val town: String,
    @Expose val county: String,
    @Expose val state: String,
    @Expose val country: String,
    @Expose val countryCode: String,
    @Expose val city: String
): Parcelable