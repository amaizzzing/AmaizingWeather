package com.amaizzzing.amaizingweather.mvp.models.entity.list

import android.graphics.Color
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DailyWeatherModel(
    var idCity: Long,
    var dt: String,
    var dtInMillis: Long,
    var sunrise: String,
    var sunset: String,
    var temp: String,
    var feelsLike: String,
    var pressure: String,
    var humidity: String,
    var windSpeed: String,
    var weather: String,
    var isSelect: Boolean = false,
    var isHourlyImageGone: Int = 8,
    var hourlyForecastImage: Int = 0,
    var imageDaily: Int = 0,
    var mainImage: Int = 0,
    var backgroundColor: Int = if(isSelect) Color.YELLOW else Color.TRANSPARENT
): Parcelable