package com.amaizzzing.amaizingweather.mvp.models.repositories.room

import com.amaizzzing.amaizingweather.mvp.models.entity.cache.HistoryCityDbEntity
import com.amaizzzing.amaizingweather.mvp.models.entity.list.model_factory.ICityModelFactory
import com.amaizzzing.amaizingweather.mvp.models.room.db.WeatherDatabase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class HistoryCache(
        val db: WeatherDatabase,
        val cityModelFactory: ICityModelFactory
): IHistoryCache {
    override fun insert(idCity: Long) =
        Completable.fromAction {
            db.runInTransaction {
                db.historyDao.deleteByIdCity(idCity)
                db.historyDao.insert(
                    HistoryCityDbEntity(
                        0,
                        idCity
                    )
                )
            }
        }.subscribeOn(Schedulers.io())

    override fun getLastCity(limit: Int) =
        Single.fromCallable {
            db.historyDao.getLastCity(limit).map {
                cityModelFactory.getCityModelFromDbEntity(it)
            }
        }.subscribeOn(Schedulers.io())
}