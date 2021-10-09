package com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.weather

import com.google.gson.annotations.Expose

data class FeelsLikeResponse(
    @Expose var day: Double = 0.0,
    @Expose var night: Double = 0.0,
    @Expose var eve: Double = 0.0,
    @Expose var morn: Double = 0.0
) {
}