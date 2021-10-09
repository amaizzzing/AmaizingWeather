package com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.weather

import com.google.gson.annotations.Expose

data class AllWeatherObjectResponse(
    @Expose var lat: Double,
    @Expose var lon: Double,
    @Expose val current: CurrentWeatherResponse,
    @Expose var hourly: MutableList<HourlyResponse>,
    @Expose var daily: MutableList<DailyResponse>,
    @Expose var alerts: MutableList<AlertResponse>
)