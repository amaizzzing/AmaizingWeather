package com.amaizzzing.amaizingweather.ui.image.weather_strategy

interface WeatherImageStrategy {
    fun getDailyWeatherImage(): Int

    fun getMainWeatherImage(): Int
}