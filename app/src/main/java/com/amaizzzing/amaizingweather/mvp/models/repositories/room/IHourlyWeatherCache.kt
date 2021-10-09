package com.amaizzzing.amaizingweather.mvp.models.repositories.room

import com.amaizzzing.amaizingweather.mvp.models.entity.cache.HourlyWeatherDbEntity
import com.amaizzzing.amaizingweather.mvp.models.entity.list.HourlyWeatherModel
import com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.weather.HourlyResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IHourlyWeatherCache {
    fun insert(idCity: Long, hourlyWeather: List<HourlyResponse>): Completable

    fun getByIdCity(idCity: Long): Single<List<HourlyWeatherDbEntity>>

    fun getHourlyForDay(idCity: Long, date: Long): Single<List<HourlyWeatherModel>>
}