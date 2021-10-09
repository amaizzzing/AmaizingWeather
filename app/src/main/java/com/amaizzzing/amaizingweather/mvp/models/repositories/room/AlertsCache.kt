package com.amaizzzing.amaizingweather.mvp.models.repositories.room

import com.amaizzzing.amaizingweather.mvp.models.entity.cache.AlertsDbEntity
import com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.weather.AlertResponse
import com.amaizzzing.amaizingweather.mvp.models.room.db.WeatherDatabase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class AlertsCache(val db: WeatherDatabase): IAlertsCache {
    override fun insert(idCity: Long, alerts: List<AlertResponse>) = Completable.fromAction {
        val alertsRoom = alerts.map {
            AlertsDbEntity(
                id = 0,
                senderName = it.senderName,
                event = it.event,
                start = it.start,
                end = it.end,
                description = it.description,
                idCity = idCity
            )
        }
        db.alertsDao.insert(alertsRoom)
    }.subscribeOn(Schedulers.io())

    override fun getByIdCity(idCity: Long) =
        Single.fromCallable {
            return@fromCallable db.alertsDao.getByIdCity(idCity)
        }.subscribeOn(Schedulers.io())
}