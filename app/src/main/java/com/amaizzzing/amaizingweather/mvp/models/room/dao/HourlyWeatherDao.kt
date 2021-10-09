package com.amaizzzing.amaizingweather.mvp.models.room.dao

import androidx.room.*
import com.amaizzzing.amaizingweather.mvp.models.entity.cache.HourlyWeatherDbEntity

@Dao
interface HourlyWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weathers: List<HourlyWeatherDbEntity>)

    @Query("SELECT * FROM HourlyWeatherDbEntity")
    fun getAll(): List<HourlyWeatherDbEntity>

    @Query("SELECT * FROM HourlyWeatherDbEntity WHERE idCity = :idCity")
    fun getByIdCity(idCity: Long): List<HourlyWeatherDbEntity>

    @Query("SELECT * FROM HourlyWeatherDbEntity WHERE idCity = :idCity AND (dt BETWEEN :dateStart AND :dateEnd)")
    fun getHourlyForDay(idCity: Long, dateStart: Long, dateEnd: Long): List<HourlyWeatherDbEntity>
}