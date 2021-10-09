package com.amaizzzing.amaizingweather.mvp.models.entity.list.model_factory

import com.amaizzzing.amaizingweather.ui.image.weather_strategy.WeatherImageStrategy

interface IBaseWeatherModelFactory {
    fun getWeatherImageStrategy(weather: String): WeatherImageStrategy
}