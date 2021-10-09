package com.amaizzzing.amaizingweather.mvp.models.entity.list.model_factory

import com.amaizzzing.amaizingweather.AmaizingWeatherApp
import com.amaizzzing.amaizingweather.Constants.CLEAR_WEATHER_TYPE
import com.amaizzzing.amaizingweather.Constants.CLOUDS_WEATHER_TYPE
import com.amaizzzing.amaizingweather.Constants.CURRENT_WEATHER_TITLE
import com.amaizzzing.amaizingweather.Constants.RAIN_WEATHER_TYPE
import com.amaizzzing.amaizingweather.DateUtils
import com.amaizzzing.amaizingweather.StringUtils
import com.amaizzzing.amaizingweather.di.StringQualifier
import com.amaizzzing.amaizingweather.mvp.models.entity.cache.DailyWeatherDbEntity
import com.amaizzzing.amaizingweather.mvp.models.entity.list.DailyWeatherModel
import com.amaizzzing.amaizingweather.mvp.models.entity.list.HourlyWeatherModel
import com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.weather.CurrentWeatherResponse
import com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.weather.DailyResponse
import com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.weather.HourlyResponse
import com.amaizzzing.amaizingweather.ui.image.hourly_strategy.DisableHourlyImageStrategy
import com.amaizzzing.amaizingweather.ui.image.hourly_strategy.EnableHourlyImageStrategy
import com.amaizzzing.amaizingweather.ui.image.hourly_strategy.HourlyImageStrategy
import com.amaizzzing.amaizingweather.ui.image.weather_strategy.WeatherImageStrategy
import javax.inject.Inject

class DailyWeatherModelFactory: IDailyWeatherModelFactory {
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

    override fun getHourlyImageStrategy(dailyDate: Long, hourlyTimes: List<Long>): Pair<Int, HourlyImageStrategy> {
        val startDay = DateUtils.atStartOfDay(dailyDate)

        val endDay = DateUtils.atEndOfDay(dailyDate)

        val hourlyInTheDay = hourlyTimes.filter { it in startDay..endDay }.size

        val isHourlyImageGone: Int

        val hourlyImageStrategy = if (hourlyInTheDay > 0) {
            isHourlyImageGone = 0
            EnableHourlyImageStrategy()
        } else {
            isHourlyImageGone = 8
            DisableHourlyImageStrategy()
        }

        return isHourlyImageGone to hourlyImageStrategy
    }

    override fun getDailyWeatherModelFromResponse(idCity: Long, dailyResponse: DailyResponse, hourlyResponse: List<HourlyResponse>): DailyWeatherModel {
        val weatherImageStrategy = getWeatherImageStrategy(dailyResponse.weather[0].main)

        val hourlyImageStrategy = getHourlyImageStrategy(dailyResponse.dt * 1000, hourlyResponse.map { it.dt * 1000 })

        return DailyWeatherModel(
            dt = DateUtils.millisToDateString(dailyResponse.dt * 1000),
            dtInMillis = dailyResponse.dt * 1000,
            sunrise = DateUtils.millisToTimeString(dailyResponse.sunrise * 1000),
            sunset = DateUtils.millisToTimeString(dailyResponse.sunset * 1000),
            temp = StringUtils.correctTemp(dailyResponse.temp.day) ,
            feelsLike = StringUtils.correctTemp(dailyResponse.feelsLike.day) ,
            pressure = dailyResponse.pressure.toString(),
            humidity = StringUtils.correctHumidity(dailyResponse.humidity),
            windSpeed = StringUtils.correctWindSpeed(dailyResponse.windSpeed),
            weather = dailyResponse.weather[0].description,
            isSelect = false,
            hourlyForecastImage = hourlyImageStrategy.second.getHourlyImage(),
            isHourlyImageGone = hourlyImageStrategy.first,
            imageDaily = weatherImageStrategy.getDailyWeatherImage(),
            mainImage = weatherImageStrategy.getMainWeatherImage(),
            idCity = idCity
        )
    }

    override fun getDailyWeatherModelFromCurrentResponse(idCity: Long, dailyResponse: CurrentWeatherResponse): DailyWeatherModel {
        val weatherImageStrategy = getWeatherImageStrategy(dailyResponse.weather[0].main)

        return DailyWeatherModel(
                dt = CURRENT_WEATHER_TITLE,
                dtInMillis = dailyResponse.dt * 1000,
                sunrise = DateUtils.millisToTimeString(dailyResponse.sunrise * 1000),
                sunset = DateUtils.millisToTimeString(dailyResponse.sunset * 1000),
                temp = StringUtils.correctTemp(dailyResponse.temp) ,
                feelsLike = StringUtils.correctTemp(dailyResponse.feelsLike) ,
                pressure = dailyResponse.pressure.toString(),
                humidity = StringUtils.correctHumidity(dailyResponse.humidity),
                windSpeed = StringUtils.correctWindSpeed(dailyResponse.windSpeed),
                weather = dailyResponse.weather[0].description,
                isSelect = true,
                hourlyForecastImage = 0,
                isHourlyImageGone = 8,
                imageDaily = weatherImageStrategy.getDailyWeatherImage(),
                mainImage = weatherImageStrategy.getMainWeatherImage(),
                idCity = idCity
        )
    }

    override fun getDailyWeatherModelFromDbEntity(dailyentity: DailyWeatherDbEntity, hourlyEntity: List<HourlyWeatherModel>): DailyWeatherModel {
        val weatherImageStrategy = getWeatherImageStrategy(dailyentity.weather)

        val hourlyImageStrategy = getHourlyImageStrategy(dailyentity.dt, hourlyEntity.map { it.timeInMillist })

        return DailyWeatherModel(
            dt = DateUtils.millisToDateString(dailyentity.dt),
            dtInMillis = dailyentity.dt,
            sunrise = DateUtils.millisToTimeString(dailyentity.sunrise),
            sunset = DateUtils.millisToTimeString(dailyentity.sunset),
            temp = StringUtils.correctTemp(dailyentity.temp.toDouble()) ,
            feelsLike = StringUtils.correctTemp(dailyentity.feelsLike.toDouble()) ,
            pressure = dailyentity.pressure.toString(),
            humidity = StringUtils.correctHumidity(dailyentity.humidity),
            windSpeed = StringUtils.correctWindSpeed(dailyentity.windSpeed),
            weather = dailyentity.weather,
            isSelect = false,
            hourlyForecastImage = hourlyImageStrategy.second.getHourlyImage(),
            isHourlyImageGone = hourlyImageStrategy.first,
            imageDaily = weatherImageStrategy.getDailyWeatherImage(),
            mainImage = weatherImageStrategy.getMainWeatherImage(),
            idCity = dailyentity.idCity
        )
    }
}