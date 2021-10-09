package com.amaizzzing.amaizingweather.mvp.models.entity.list.model_factory

import com.amaizzzing.amaizingweather.AmaizingWeatherApp
import com.amaizzzing.amaizingweather.Constants.CLEAR_WEATHER_TYPE
import com.amaizzzing.amaizingweather.Constants.CLOUDS_WEATHER_TYPE
import com.amaizzzing.amaizingweather.Constants.RAIN_WEATHER_TYPE
import com.amaizzzing.amaizingweather.DateUtils
import com.amaizzzing.amaizingweather.StringUtils
import com.amaizzzing.amaizingweather.di.StringQualifier
import com.amaizzzing.amaizingweather.mvp.models.entity.cache.HourlyWeatherDbEntity
import com.amaizzzing.amaizingweather.mvp.models.entity.list.HourlyWeatherModel
import com.amaizzzing.amaizingweather.ui.image.weather_strategy.WeatherImageStrategy
import javax.inject.Inject

class HourlyWeatherModelFactory: IHourlyWeatherModelFactory {
    @Inject
    @field:StringQualifier(CLEAR_WEATHER_TYPE)
    lateinit var clearWeatherStrategy: WeatherImageStrategy

    @Inject
    @field:StringQualifier(CLOUDS_WEATHER_TYPE)
    lateinit var peremenWeatherStrategy: WeatherImageStrategy

    @Inject
    @field:StringQualifier(RAIN_WEATHER_TYPE)
    lateinit var rainWeatherStrategy: WeatherImageStrategy

    init {
        AmaizingWeatherApp.instance.appComponent.inject(this)
    }

    override fun getWeatherImageStrategy(weather: String): WeatherImageStrategy =
        when(weather) {
            CLEAR_WEATHER_TYPE -> {
                clearWeatherStrategy
            }
            CLOUDS_WEATHER_TYPE -> {
                peremenWeatherStrategy
            }
            RAIN_WEATHER_TYPE -> {
                rainWeatherStrategy
            }
            else -> {
                clearWeatherStrategy
            }
        }

    override fun getHourlyWeatherModelFromDbEntity(entity: HourlyWeatherDbEntity): HourlyWeatherModel {
        val weatherImageStrategy = getWeatherImageStrategy(entity.weather)

        return HourlyWeatherModel(
            temp = StringUtils.correctTemp(entity.temp.toDouble()),
            time = DateUtils.millisToTimeString(entity.dt),
            timeInMillist = entity.dt,
            image = weatherImageStrategy.getDailyWeatherImage(),
            wind = StringUtils.correctWindSpeed(entity.windSpeed),
            weather = entity.weather,
        )
    }
}