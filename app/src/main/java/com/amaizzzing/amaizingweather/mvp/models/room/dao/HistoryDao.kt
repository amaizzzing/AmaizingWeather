package com.amaizzzing.amaizingweather.mvp.models.room.dao

import androidx.room.*
import com.amaizzzing.amaizingweather.mvp.models.entity.cache.CityDbEntity
import com.amaizzzing.amaizingweather.mvp.models.entity.cache.HistoryCityDbEntity

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(history: HistoryCityDbEntity)

    @Query("DELETE FROM HistoryCityDbEntity WHERE idCity = :idCity")
    fun deleteByIdCity(idCity: Long)

    @Query("SELECT * FROM HistoryCityDbEntity")
    fun getAll(): List<HistoryCityDbEntity>

    @Query(
        "SELECT c.*\n" +
        "FROM CityDbEntity as c\n" +
        "INNER JOIN HistoryCityDbEntity AS h ON c.idCity=h.idCity\n" +
        "ORDER BY h.id DESC\n" +
        "LIMIT :limit"
    )
    fun getLastCity(limit: Int): List<CityDbEntity>
}