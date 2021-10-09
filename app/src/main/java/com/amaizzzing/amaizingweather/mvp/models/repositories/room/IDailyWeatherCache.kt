package com.amaizzzing.amaizingweather.mvp.models.repositories.room

import com.amaizzzing.amaizingweather.mvp.models.entity.cache.DailyWeatherDbEntity
import com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.weather.DailyResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IDailyWeatherCache {
    fun insert(idCity: Long, dailyWeather: List<DailyResponse>): Completable

    fun getByIdCity(idCity: Long): Single<List<DailyWeatherDbEntity>>
}