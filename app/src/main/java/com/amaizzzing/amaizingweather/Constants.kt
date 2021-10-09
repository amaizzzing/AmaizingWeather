package com.amaizzzing.amaizingweather

object Constants {
    const val BASE_WEATHER_URL = "https://api.openweathermap.org/data/2.5/"

    const val BASE_CITIES_URL = "https://nominatim.openstreetmap.org/"

    const val WEATHER_API_KEY: String = BuildConfig.WEATHER_API_KEY

    const val GEONAMES_API_KEY: String = BuildConfig.GEONAMES_API_KEY

    const val REQUEST_KEY_CHOOSE_CITY = "REQUEST_KEY_CHOOSE_CITY"

    const val CHOOSE_CITY_KEY = "CHOOSE_CITY_KEY"

    const val CLEAR_WEATHER_TYPE = "Clear"

    const val CLOUDS_WEATHER_TYPE = "Clouds"

    const val RAIN_WEATHER_TYPE = "Rain"

    const val CURRENT_WEATHER_TITLE = "Сейчас"

    const val CITY_API_FORMAT = "jsonv2"

    const val CITY_API_LIMIT = "20"

    const val CITY_API_DETAILS = "1"

    const val WEATHER_API_EXCLUDE = "minutely"

    const val WEATHER_API_UNITS = "metric"

    const val WEATHER_API_LANGUAGE = "ru"
}