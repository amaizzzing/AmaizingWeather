package com.amaizzzing.amaizingweather.mvp.models.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amaizzzing.amaizingweather.mvp.models.entity.cache.*
import com.amaizzzing.amaizingweather.mvp.models.room.dao.*


@Database(
        entities = [
            AlertsDbEntity::class,
            CityDbEntity::class,
            DailyWeatherDbEntity::class,
            HourlyWeatherDbEntity::class,
            HistoryCityDbEntity::class
        ],
        version = 1,
        exportSchema = false
)

abstract class WeatherDatabase : RoomDatabase() {
    abstract val alertsDao: AlertsDao
    abstract val cityDao: CityDao
    abstract val dailyWeatherDao: DailyWeatherDao
    abstract val hourlyWeatherDao: HourlyWeatherDao
    abstract val historyDao: HistoryDao

    companion object {
        const val DB_NAME = "weather.db"

        const val DB_FIRST_LOAD_PATH = "database/pre_weather.db"

        private var instance: WeatherDatabase? = null

        fun getInstance() = instance ?: throw RuntimeException("Database has not been created. Please call create(context)")
    }
}