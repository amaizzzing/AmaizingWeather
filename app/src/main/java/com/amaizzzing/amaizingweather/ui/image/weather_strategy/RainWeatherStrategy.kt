package com.amaizzzing.amaizingweather.ui.image.weather_strategy

import com.amaizzzing.amaizingweather.R

class RainWeatherStrategy: WeatherImageStrategy {
    override fun getDailyWeatherImage() = R.drawable.rain

    override fun getMainWeatherImage() = R.drawable.rain_main
}