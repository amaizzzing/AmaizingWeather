package com.amaizzzing.amaizingweather.mvp.view

import com.amaizzzing.amaizingweather.mvp.models.entity.list.CityModel
import com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.city.GeonameCity
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface SearchCityView: MvpView {
    fun init()
    fun updateList()
    fun sendChosenCity(city: CityModel)
    fun showLoad()
    fun endLoad()
    fun notResult()
}