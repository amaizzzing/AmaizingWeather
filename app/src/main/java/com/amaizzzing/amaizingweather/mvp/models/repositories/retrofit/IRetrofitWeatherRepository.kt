package com.amaizzzing.amaizingweather.mvp.models.repositories.retrofit

import com.amaizzzing.amaizingweather.mvp.models.entity.list.DailyWeatherModel
import io.reactivex.rxjava3.core.Single

interface IRetrofitWeatherRepository {
    fun getWeather(idCity: Long, lat: Double, lon: Double): Single<List<DailyWeatherModel>>
}