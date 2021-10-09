package com.amaizzzing.amaizingweather.di.modules

import com.amaizzzing.amaizingweather.Constants.CLEAR_WEATHER_TYPE
import com.amaizzzing.amaizingweather.Constants.CLOUDS_WEATHER_TYPE
import com.amaizzzing.amaizingweather.Constants.RAIN_WEATHER_TYPE
import com.amaizzzing.amaizingweather.di.StringQualifier
import com.amaizzzing.amaizingweather.mvp.models.entity.list.model_factory.*
import com.amaizzzing.amaizingweather.ui.image.weather_strategy.ClearWeatherStrategy
import com.amaizzzing.amaizingweather.ui.image.weather_strategy.PeremenWeatherStrategy
import com.amaizzzing.amaizingweather.ui.image.weather_strategy.RainWeatherStrategy
import com.amaizzzing.amaizingweather.ui.image.weather_strategy.WeatherImageStrategy
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModelsModule {
    @StringQualifier(CLEAR_WEATHER_TYPE)
    @Provides
    @Singleton
    fun geClearWeatherStrategy(): WeatherImageStrategy = ClearWeatherStrategy()

    @StringQualifier(CLOUDS_WEATHER_TYPE)
    @Provides
    @Singleton
    fun getPeremenWeatherStrategy(): WeatherImageStrategy = PeremenWeatherStrategy()

    @StringQualifier(RAIN_WEATHER_TYPE)
    @Provides
    @Singleton
    fun getRainWeatherStrategy(): WeatherImageStrategy = RainWeatherStrategy()

    @Provides
    @Singleton
    fun dailyWeatherModelFactory(): IDailyWeatherModelFactory = DailyWeatherModelFactory()

    @Provides
    @Singleton
    fun hourlyWeatherModelFactory(): IHourlyWeatherModelFactory = HourlyWeatherModelFactory()

    @Provides
    @Singleton
    fun cityModelFactory(): ICityModelFactory = CityModelFactory()
}