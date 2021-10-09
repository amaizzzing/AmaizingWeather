package com.amaizzzing.amaizingweather.mvp.models.repositories.room

import com.amaizzzing.amaizingweather.DateUtils
import com.amaizzzing.amaizingweather.mvp.models.entity.cache.HourlyWeatherDbEntity
import com.amaizzzing.amaizingweather.mvp.models.entity.list.model_factory.IHourlyWeatherModelFactory
import com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.weather.HourlyResponse
import com.amaizzzing.amaizingweather.mvp.models.room.db.WeatherDatabase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class HourlyWeatherCache(
        val db: WeatherDatabase,
        val hourlyWeatherModelFactory: IHourlyWeatherModelFactory
): IHourlyWeatherCache {
    override fun insert(idCity: Long, hourlyWeather: List<HourlyResponse>) =
        Completable.fromAction {
            val hWeather = hourlyWeather.map {
                HourlyWeatherDbEntity(
                    dt = it.dt * 1000,
                    temp = it.temp.toInt(),
                    feelsLike = it.feelsLike.toInt(),
                    pressure = it.pressure,
                    humidity = it.humidity,
                    clouds = it.clouds,
                    windSpeed = it.windSpeed,
                    weather = it.weather[0].main,
                    idCity = idCity
                )
            }
            db.hourlyWeatherDao.insert(hWeather)
        }.subscribeOn(Schedulers.io())

    override fun getByIdCity(idCity: Long) =
        Single.fromCallable {
            return@fromCallable db.hourlyWeatherDao.getByIdCity(idCity)
        }.subscribeOn(Schedulers.io())

    override fun getHourlyForDay(idCity: Long, date: Long) =
        Single.fromCallable {
            return@fromCallable db.hourlyWeatherDao.getHourlyForDay(
                idCity,
                DateUtils.atStartOfDay(date),
                DateUtils.atEndOfDay(date)
            ).map {
                hourlyWeatherModelFactory.getHourlyWeatherModelFromDbEntity(it)
            }
        }.subscribeOn(Schedulers.io())
}