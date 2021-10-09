package com.amaizzzing.amaizingweather.ui.image.weather_strategy

import com.amaizzzing.amaizingweather.R

class ClearWeatherStrategy: WeatherImageStrategy {
    override fun getDailyWeatherImage() = R.drawable.clear

    override fun getMainWeatherImage() = R.drawable.clear_main
}