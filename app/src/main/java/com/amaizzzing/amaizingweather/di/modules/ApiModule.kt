package com.amaizzzing.amaizingweather.di.modules

import com.amaizzzing.amaizingweather.AmaizingWeatherApp
import com.amaizzzing.amaizingweather.Constants.BASE_CITIES_URL
import com.amaizzzing.amaizingweather.Constants.BASE_WEATHER_URL
import com.amaizzzing.amaizingweather.mvp.models.api.IGeonamesDataSource
import com.amaizzzing.amaizingweather.mvp.models.api.IWeatherDataSource
import com.amaizzzing.amaizingweather.mvp.models.network.INetworkStatus
import com.amaizzzing.amaizingweather.ui.network.AndroidNetworkStatus
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun apiWeather(gson: Gson): IWeatherDataSource = Retrofit.Builder()
        .baseUrl(BASE_WEATHER_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(IWeatherDataSource::class.java)

    @Provides
    @Singleton
    fun apiGeonames(gson: Gson): IGeonamesDataSource = Retrofit.Builder()
        .baseUrl(BASE_CITIES_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(IGeonamesDataSource::class.java)

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()

    @Provides
    @Singleton
    fun networkStatus(app: AmaizingWeatherApp): INetworkStatus = AndroidNetworkStatus(app)
}