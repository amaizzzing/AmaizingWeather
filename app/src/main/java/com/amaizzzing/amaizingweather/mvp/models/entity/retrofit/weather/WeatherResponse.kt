package com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.weather

import com.google.gson.annotations.Expose

data class WeatherResponse(
    @Expose val id: Long,
    @Expose val main: String,
    @Expose val description: String,
    @Expose val icon: String
)