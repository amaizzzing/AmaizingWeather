package com.amaizzzing.amaizingweather.mvp.models.repositories.retrofit

import com.amaizzzing.amaizingweather.Constants.WEATHER_API_EXCLUDE
import com.amaizzzing.amaizingweather.Constants.WEATHER_API_KEY
import com.amaizzzing.amaizingweather.Constants.WEATHER_API_LANGUAGE
import com.amaizzzing.amaizingweather.Constants.WEATHER_API_UNITS
import com.amaizzzing.amaizingweather.mvp.models.api.IWeatherDataSource
import com.amaizzzing.amaizingweather.mvp.models.entity.list.model_factory.IDailyWeatherModelFactory
import com.amaizzzing.amaizingweather.mvp.models.network.INetworkStatus
import com.amaizzzing.amaizingweather.mvp.models.repositories.room.IDailyWeatherCache
import com.amaizzzing.amaizingweather.mvp.models.repositories.room.IHistoryCache
import com.amaizzzing.amaizingweather.mvp.models.repositories.room.IHourlyWeatherCache
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitWeatherRepository(
        val api: IWeatherDataSource,
        private val networkStatus: INetworkStatus,
        private val dailyCache: IDailyWeatherCache,
        private val hourlyCache: IHourlyWeatherCache,
        private val historyCityCache: IHistoryCache,
        private val dailyWeatherModelFactory: IDailyWeatherModelFactory
): IRetrofitWeatherRepository {
    override fun getWeather(idCity: Long, lat: Double, lon: Double) = networkStatus.isOnlineSingle()
        .flatMap { isOnline ->
            if (isOnline) {
                api.getAllWeatherForecast(
                    lat = lat,
                    lon = lon,
                    exclude = WEATHER_API_EXCLUDE,
                    units = WEATHER_API_UNITS,
                    lang = WEATHER_API_LANGUAGE,
                    appid = WEATHER_API_KEY
                ).flatMap { weatherResponse ->
                    dailyCache.insert(
                        idCity = idCity,
                        dailyWeather = weatherResponse.daily
                    ).andThen(
                        hourlyCache.insert(
                            idCity = idCity,
                            hourlyWeather = weatherResponse.hourly
                        )
                    ).andThen(
                        historyCityCache.insert(idCity)
                    ).andThen(Single.just(weatherResponse))
                }
                .map { weatherResponse ->
                    listOf(dailyWeatherModelFactory.getDailyWeatherModelFromCurrentResponse(idCity, weatherResponse.current)) +
                        weatherResponse.daily.map { dailyWeatherModelFactory.getDailyWeatherModelFromResponse(idCity, it, weatherResponse.hourly) }
                }
            } else {
                dailyCache.getByIdCity(idCity)
                    .flatMapObservable { dailyWeatherList ->
                        Observable.fromIterable(dailyWeatherList)
                    }
                    .flatMap(
                        { daily -> hourlyCache.getHourlyForDay(idCity, daily.dt).toObservable() }
                    ) { daily, hourly ->
                        dailyWeatherModelFactory.getDailyWeatherModelFromDbEntity(daily, hourly)
                    }.toSortedList{ p1, p2 -> (p1.dtInMillis.compareTo(p2.dtInMillis))}
            }
        }
        .subscribeOn(Schedulers.io())
}