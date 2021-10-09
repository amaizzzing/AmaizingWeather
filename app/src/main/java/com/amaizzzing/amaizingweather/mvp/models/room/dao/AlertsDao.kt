package com.amaizzzing.amaizingweather.mvp.models.room.dao

import androidx.room.*
import com.amaizzzing.amaizingweather.mvp.models.entity.cache.AlertsDbEntity

@Dao
interface AlertsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(alert: AlertsDbEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg alerts: AlertsDbEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(alerts: List<AlertsDbEntity>)

    @Update
    fun update(alert: AlertsDbEntity)

    @Update
    fun update(vararg alerts: AlertsDbEntity)

    @Update
    fun update(alerts: List<AlertsDbEntity>)

    @Delete
    fun delete(alert: AlertsDbEntity)

    @Delete
    fun delete(vararg alerts: AlertsDbEntity)

    @Delete
    fun delete(alerts: List<AlertsDbEntity>)

    @Query("SELECT * FROM AlertsDbEntity")
    fun getAll(): List<AlertsDbEntity>

    @Query("SELECT * FROM AlertsDbEntity WHERE idCity = :idCity LIMIT 1")
    fun findByIdCity(idCity: String): AlertsDbEntity?

    @Query("SELECT * FROM AlertsDbEntity WHERE idCity = :idCity LIMIT 1")
    fun getByIdCity(idCity: Long): List<AlertsDbEntity>
}