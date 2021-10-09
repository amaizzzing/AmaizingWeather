package com.amaizzzing.amaizingweather.mvp.models.repositories.room

import com.amaizzzing.amaizingweather.mvp.models.entity.cache.AlertsDbEntity
import com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.weather.AlertResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IAlertsCache {
    fun insert(idCity: Long, alerts: List<AlertResponse>): Completable

    fun getByIdCity(idCity: Long): Single<List<AlertsDbEntity>>
}