package com.amaizzzing.amaizingweather.mvp.models.room.dao

import androidx.room.*
import com.amaizzzing.amaizingweather.mvp.models.entity.cache.CityDbEntity

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cities: List<CityDbEntity>)

    @Query("SELECT * FROM CityDbEntity WHERE idCity = :idCity LIMIT 1")
    fun getCityById(idCity: String): CityDbEntity?

    @Query("SELECT * FROM CityDbEntity WHERE cityName = :cityName LIMIT 1")
    fun getCityByName(cityName: String): CityDbEntity?
}