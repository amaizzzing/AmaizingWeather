package com.amaizzzing.amaizingweather.mvp.models.entity.list.model_factory

import com.amaizzzing.amaizingweather.mvp.models.entity.cache.DailyWeatherDbEntity
import com.amaizzzing.amaizingweather.mvp.models.entity.list.DailyWeatherModel
import com.amaizzzing.amaizingweather.mvp.models.entity.list.HourlyWeatherModel
import com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.weather.CurrentWeatherResponse
import com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.weather.DailyResponse
import com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.weather.HourlyResponse
import com.amaizzzing.amaizingweather.ui.image.hourly_strategy.HourlyImageStrategy

interface IDailyWeatherModelFactory: IBaseWeatherModelFactory {
    fun getHourlyImageStrategy(dailyDate: Long, hourlyTimes: List<Long>): Pair<Int, HourlyImageStrategy>

    fun getDailyWeatherModelFromResponse(idCity: Long, dailyResponse: DailyResponse, hourlyResponse: List<HourlyResponse>): DailyWeatherModel

    fun getDailyWeatherModelFromCurrentResponse(idCity: Long, dailyResponse: CurrentWeatherResponse): DailyWeatherModel

    fun getDailyWeatherModelFromDbEntity(dailyentity: DailyWeatherDbEntity, hourlyEntity: List<HourlyWeatherModel>): DailyWeatherModel
}