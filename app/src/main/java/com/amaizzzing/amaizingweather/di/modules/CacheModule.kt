package com.amaizzzing.amaizingweather.di.modules

import androidx.room.Room
import com.amaizzzing.amaizingweather.AmaizingWeatherApp
import com.amaizzzing.amaizingweather.mvp.models.entity.list.model_factory.ICityModelFactory
import com.amaizzzing.amaizingweather.mvp.models.entity.list.model_factory.IHourlyWeatherModelFactory
import com.amaizzzing.amaizingweather.mvp.models.repositories.room.*
import com.amaizzzing.amaizingweather.mvp.models.room.db.WeatherDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {
    @Provides
    @Singleton
    fun database(app: AmaizingWeatherApp): WeatherDatabase =
        Room.databaseBuilder(
            app, WeatherDatabase::class.java,
            WeatherDatabase.DB_NAME
        )
        .createFromAsset(WeatherDatabase.DB_FIRST_LOAD_PATH)
        .build()

    @Provides
    @Singleton
    fun cityCache(db: WeatherDatabase): ICityCache = CityCache(db)

    @Provides
    @Singleton
    fun alertsCache(db: WeatherDatabase): IAlertsCache = AlertsCache(db)

    @Provides
    @Singleton
    fun dailyWeatherCache(db: WeatherDatabase): IDailyWeatherCache = DailyWeatherCache(db)

    @Provides
    @Singleton
    fun hourlyWeatherCache(
        db: WeatherDatabase,
        hourlyWeatherModelFactory: IHourlyWeatherModelFactory
    ): IHourlyWeatherCache = HourlyWeatherCache(db, hourlyWeatherModelFactory)

    @Provides
    @Singleton
    fun historyCityCache(
        db: WeatherDatabase,
        cityModelFactory: ICityModelFactory
    ): IHistoryCache = HistoryCache(db, cityModelFactory)
}