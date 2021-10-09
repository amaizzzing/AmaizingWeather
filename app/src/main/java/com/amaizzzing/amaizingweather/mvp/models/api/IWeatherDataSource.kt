package com.amaizzzing.amaizingweather.mvp.models.api

import com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.weather.AllWeatherObjectResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherDataSource {
    @GET("onecall?")
    fun getAllWeatherForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("appid") appid: String
    ): Single<AllWeatherObjectResponse>
}