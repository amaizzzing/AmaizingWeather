package com.amaizzzing.amaizingweather.mvp.models.entity.list.model_factory

import com.amaizzzing.amaizingweather.mvp.models.entity.cache.CityDbEntity
import com.amaizzzing.amaizingweather.mvp.models.entity.list.CityModel
import com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.city.GeonameCity

interface ICityModelFactory {
    fun getCityModelFromResponse(response: GeonameCity): CityModel

    fun getCityModelFromDbEntity(entity: CityDbEntity): CityModel
}