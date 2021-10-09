package com.amaizzzing.amaizingweather.mvp.models.room.dao

import androidx.room.*
import com.amaizzzing.amaizingweather.mvp.models.entity.cache.DailyWeatherDbEntity

@Dao
interface DailyWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weathers: List<DailyWeatherDbEntity>)

    @Query("SELECT * FROM DailyWeatherDbEntity")
    fun getAll(): List<DailyWeatherDbEntity>

    @Query("SELECT * FROM DailyWeatherDbEntity WHERE idCity = :idCity")
    fun getByIdCity(idCity: Long): List<DailyWeatherDbEntity>
}