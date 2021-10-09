package com.amaizzzing.amaizingweather.mvp.navigation

import com.amaizzzing.amaizingweather.mvp.models.entity.list.DailyWeatherModel
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun mainWeather(): Screen

    fun hourlyWeather(idCity: Long, daily: DailyWeatherModel): Screen

    fun searchCity(): Screen
}