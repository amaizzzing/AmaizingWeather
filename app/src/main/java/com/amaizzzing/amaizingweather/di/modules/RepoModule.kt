package com.amaizzzing.amaizingweather.di.modules

import com.amaizzzing.amaizingweather.mvp.models.api.IGeonamesDataSource
import com.amaizzzing.amaizingweather.mvp.models.api.IWeatherDataSource
import com.amaizzzing.amaizingweather.mvp.models.entity.list.model_factory.ICityModelFactory
import com.amaizzzing.amaizingweather.mvp.models.entity.list.model_factory.IDailyWeatherModelFactory
import com.amaizzzing.amaizingweather.mvp.models.network.INetworkStatus
import com.amaizzzing.amaizingweather.mvp.models.repositories.retrofit.IRetrofitCityRepository
import com.amaizzzing.amaizingweather.mvp.models.repositories.retrofit.IRetrofitWeatherRepository
import com.amaizzzing.amaizingweather.mvp.models.repositories.retrofit.RetrofitCityRepository
import com.amaizzzing.amaizingweather.mvp.models.repositories.retrofit.RetrofitWeatherRepository
import com.amaizzzing.amaizingweather.mvp.models.repositories.room.ICityCache
import com.amaizzzing.amaizingweather.mvp.models.repositories.room.IDailyWeatherCache
import com.amaizzzing.amaizingweather.mvp.models.repositories.room.IHistoryCache
import com.amaizzzing.amaizingweather.mvp.models.repositories.room.IHourlyWeatherCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Provides
    @Singleton
    fun weatherRepository(
            api: IWeatherDataSource,
            networkStatus: INetworkStatus,
            dailyCache: IDailyWeatherCache,
            hourlyCache: IHourlyWeatherCache,
            historyCache: IHistoryCache,
            dailyWeatherModelFactory: IDailyWeatherModelFactory
    ): IRetrofitWeatherRepository =
        RetrofitWeatherRepository(
            api,
            networkStatus,
            dailyCache,
            hourlyCache,
            historyCache,
            dailyWeatherModelFactory
        )

    @Provides
    @Singleton
    fun cityRepository(
        api: IGeonamesDataSource,
        networkStatus: INetworkStatus,
        cache: ICityCache,
        cityModelFactory: ICityModelFactory
    ): IRetrofitCityRepository =
        RetrofitCityRepository(api, networkStatus, cache, cityModelFactory)
}