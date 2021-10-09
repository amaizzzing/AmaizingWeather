package com.amaizzzing.amaizingweather.mvp.models.entity.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CityDbEntity(
    @PrimaryKey
    val idCity: Long,
    val cityName: String,
    val fullCityName: String,
    val lat: Double,
    val lon: Double
)