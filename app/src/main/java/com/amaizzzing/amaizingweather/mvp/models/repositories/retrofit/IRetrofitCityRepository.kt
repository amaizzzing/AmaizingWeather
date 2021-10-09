package com.amaizzzing.amaizingweather.mvp.models.repositories.retrofit

import com.amaizzzing.amaizingweather.mvp.models.entity.list.CityModel
import io.reactivex.rxjava3.core.Single

interface IRetrofitCityRepository {
    fun getCityByName(cityName: String): Single<List<CityModel>>
}