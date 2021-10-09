package com.amaizzzing.amaizingweather.mvp.models.api

import com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.city.GeonameCity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IGeonamesDataSource {
    @GET("/")
    fun getCitiesByName(
        @Query("city") nameCity: String,
        @Query("format") format: String,
        @Query("limit") limit: String,
        @Query("addressdetails")addressDetails: String
    ): Single<List<GeonameCity>>
}