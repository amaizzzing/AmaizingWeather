package com.amaizzzing.amaizingweather

import kotlin.math.roundToInt

object StringUtils {
    fun correctTemp(temp: Double) =
        "${if (temp >= 0) "+" else ""}${temp.roundToInt()}°C"

    fun correctWindSpeed(windSpeed: Double) =
        "${windSpeed.roundToInt()} м/с"

    fun correctHumidity(humidity: Int) =
        "$humidity %"
}