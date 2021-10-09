package com.amaizzzing.amaizingweather.mvp.models.repositories.room

import com.amaizzzing.amaizingweather.mvp.models.entity.cache.DailyWeatherDbEntity
import com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.weather.DailyResponse
import com.amaizzzing.amaizingweather.mvp.models.room.db.WeatherDatabase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class DailyWeatherCache(val db: WeatherDatabase): IDailyWeatherCache {
    override fun insert(idCity: Long, dailyWeather: List<DailyResponse>) =
        Completable.fromAction {
            val dWeather = dailyWeather.map {
                DailyWeatherDbEntity(
                    dt = it.dt * 1000,
                    sunrise = it.sunrise * 1000,
                    sunset = it.sunset * 1000,
                    temp = it.temp.day.toInt(),
                    feelsLike = it.feelsLike.day.toInt(),
                    pressure = it.pressure,
                    humidity = it.humidity,
                    clouds = it.clouds,
                    windSpeed = it.windSpeed,
                    weather = it.weather[0].main,
                    idCity = idCity
                )
            }
            db.dailyWeatherDao.insert(dWeather)
        }.subscribeOn(Schedulers.io())

    override fun getByIdCity(idCity: Long) =
        Single.fromCallable {
            return@fromCallable db.dailyWeatherDao.getByIdCity(idCity)
        }.subscribeOn(Schedulers.io())
}