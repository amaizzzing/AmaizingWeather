package com.amaizzzing.amaizingweather.ui.navigation

import com.amaizzzing.amaizingweather.mvp.models.entity.list.DailyWeatherModel
import com.amaizzzing.amaizingweather.mvp.navigation.IScreens
import com.amaizzzing.amaizingweather.ui.fragment.HourlyWeatherFragment
import com.amaizzzing.amaizingweather.ui.fragment.MainWeatherFragment
import com.amaizzzing.amaizingweather.ui.fragment.SearchCityFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens: IScreens {
    override fun mainWeather(): FragmentScreen =
        FragmentScreen { MainWeatherFragment.newInstance() }

    override fun hourlyWeather(idCity: Long, daily: DailyWeatherModel): FragmentScreen =
        FragmentScreen { HourlyWeatherFragment.newInstance(idCity, daily)}

    override fun searchCity(): FragmentScreen =
        FragmentScreen { SearchCityFragment.newInstance()}

}