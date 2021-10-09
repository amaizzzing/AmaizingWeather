package com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.weather

import com.google.gson.annotations.Expose

data class DailyResponse(
        @Expose var dt: Long,
        @Expose var sunrise: Long,
        @Expose var sunset: Long,
        @Expose var temp: TempResponse,
        @Expose var feelsLike: FeelsLikeResponse,
        @Expose var pressure: Int,
        @Expose var humidity: Int,
        @Expose var dewPoint: Double,
        @Expose var uvi: Double,
        @Expose var clouds: Int,
        @Expose var windSpeed: Double,
        @Expose var windDeg: Int,
        @Expose var weather: MutableList<WeatherResponse>,
        @Expose var pop: Double,
        @Expose var snow: Double
) {
}