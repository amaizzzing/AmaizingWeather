package com.amaizzzing.amaizingweather.mvp.models.entity.list.model_factory

import com.amaizzzing.amaizingweather.mvp.models.entity.cache.CityDbEntity
import com.amaizzzing.amaizingweather.mvp.models.entity.list.CityModel
import com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.city.GeonameCity

class CityModelFactory: ICityModelFactory {
    override fun getCityModelFromResponse(response: GeonameCity)=
        CityModel(
            cityId = response.place_id,
            lat = response.lat,
            lon = response.lon,
            cityName = response.display_name.substringBefore(","),
            fullCityName = response.display_name
        )

    override fun getCityModelFromDbEntity(entity: CityDbEntity) =
        CityModel(
            cityId = entity.idCity,
            lat = entity.lat,
            lon = entity.lon,
            cityName = entity.cityName,
            fullCityName = entity.fullCityName
        )
}