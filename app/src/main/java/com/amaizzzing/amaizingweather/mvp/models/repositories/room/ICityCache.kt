package com.amaizzzing.amaizingweather.mvp.models.repositories.room

import com.amaizzzing.amaizingweather.mvp.models.entity.cache.CityDbEntity
import com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.city.GeonameCity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface ICityCache {
    fun insert(cities: List<GeonameCity>): Completable

    fun getCityByName(cityName: String): Single<CityDbEntity?>
}