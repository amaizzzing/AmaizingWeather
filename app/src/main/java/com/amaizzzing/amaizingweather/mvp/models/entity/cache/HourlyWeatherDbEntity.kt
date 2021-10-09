package com.amaizzzing.amaizingweather.mvp.models.entity.cache

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["dt", "idCity"],
    foreignKeys = [
        ForeignKey(
            entity = CityDbEntity::class,
            parentColumns = ["idCity"],
            childColumns = ["idCity"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class HourlyWeatherDbEntity (
    val dt: Long,
    val temp: Int,
    val feelsLike: Int,
    val pressure: Int,
    val humidity: Int,
    val clouds: Int,
    val windSpeed: Double,
    val weather: String,

    val idCity: Long
)