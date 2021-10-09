package com.amaizzzing.amaizingweather.mvp.view

import com.amaizzzing.amaizingweather.mvp.models.entity.list.DailyWeatherModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainWeatherView: MvpView {
    fun init()
    fun updateList()
    fun updateDailyWeather(cityName: String, weatherResponse: DailyWeatherModel)
    @StateStrategyType(SkipStrategy::class)
    fun showRefreshSnackbar()
    fun showLoad()
    fun endLoad()
}