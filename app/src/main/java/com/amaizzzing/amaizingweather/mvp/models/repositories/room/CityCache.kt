package com.amaizzzing.amaizingweather.mvp.models.repositories.room

import com.amaizzzing.amaizingweather.mvp.models.entity.cache.CityDbEntity
import com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.city.GeonameCity
import com.amaizzzing.amaizingweather.mvp.models.room.db.WeatherDatabase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class CityCache(val db: WeatherDatabase): ICityCache {
    override fun insert(cities: List<GeonameCity>) = Completable.fromAction{
        val cityList = cities.map {
            CityDbEntity(
                idCity = it.place_id,
                cityName = it.display_name.substringBefore(",").toLowerCase(),
                fullCityName = it.display_name,
                lat = it.lat,
                lon = it.lon
            )
        }
        db.cityDao.insert(cityList)
    }.subscribeOn(Schedulers.io())

    override fun getCityByName(cityName: String) =
        Single.fromCallable {
            return@fromCallable db.cityDao.getCityByName(cityName)
        }.subscribeOn(Schedulers.io())
}