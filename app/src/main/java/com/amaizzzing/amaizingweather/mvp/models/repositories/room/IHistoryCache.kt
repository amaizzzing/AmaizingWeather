package com.amaizzzing.amaizingweather.mvp.models.repositories.room

import com.amaizzzing.amaizingweather.mvp.models.entity.list.CityModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IHistoryCache {
    fun insert(idCity: Long): Completable

    fun getLastCity(limit: Int): Single<List<CityModel>>
}