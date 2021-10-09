package com.amaizzzing.amaizingweather.mvp.models.entity.list

data class HourlyWeatherModel(
    val temp: String,
    val time: String,
    val timeInMillist: Long,
    var image: Int,
    val wind: String,
    val weather: String
)