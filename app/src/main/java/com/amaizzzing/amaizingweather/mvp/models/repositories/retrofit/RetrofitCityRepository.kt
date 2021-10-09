package com.amaizzzing.amaizingweather.mvp.models.repositories.retrofit

import com.amaizzzing.amaizingweather.Constants.CITY_API_DETAILS
import com.amaizzzing.amaizingweather.Constants.CITY_API_FORMAT
import com.amaizzzing.amaizingweather.Constants.CITY_API_LIMIT
import com.amaizzzing.amaizingweather.mvp.models.api.IGeonamesDataSource
import com.amaizzzing.amaizingweather.mvp.models.entity.list.model_factory.ICityModelFactory
import com.amaizzzing.amaizingweather.mvp.models.network.INetworkStatus
import com.amaizzzing.amaizingweather.mvp.models.repositories.room.ICityCache
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitCityRepository(
        val api: IGeonamesDataSource,
        val networkStatus: INetworkStatus,
        val cache: ICityCache,
        val cityModelFactory: ICityModelFactory
): IRetrofitCityRepository {
    override fun getCityByName(cityName: String) =
        networkStatus.isOnlineSingle()
            .flatMap { isOnline ->
                if (isOnline){
                    api.getCitiesByName(
                        nameCity = cityName,
                        format = CITY_API_FORMAT,
                        limit = CITY_API_LIMIT,
                        addressDetails = CITY_API_DETAILS
                    ).flatMap { cityList ->
                        cache.insert(cityList).andThen(
                            Single.just(
                                cityList.map { item ->
                                    cityModelFactory.getCityModelFromResponse(item)
                                }
                            )
                        )
                    }
                } else {
                    cache.getCityByName(cityName).flatMap {
                        it?.let {
                            Single.just(listOf(cityModelFactory.getCityModelFromDbEntity(it)))
                        }
                    }
                }
            }.subscribeOn(Schedulers.io())
}