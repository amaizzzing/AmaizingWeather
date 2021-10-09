package com.amaizzzing.amaizingweather.mvp.models.entity.list.model_factory

import com.amaizzzing.amaizingweather.mvp.models.entity.cache.HourlyWeatherDbEntity
import com.amaizzzing.amaizingweather.mvp.models.entity.list.HourlyWeatherModel

interface IHourlyWeatherModelFactory: IBaseWeatherModelFactory {
    fun getHourlyWeatherModelFromDbEntity(entity: HourlyWeatherDbEntity): HourlyWeatherModel
}