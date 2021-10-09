package com.amaizzzing.amaizingweather.mvp.view.list

interface DailyWeatherItemView: IItemView {
    fun setDate(text: String)
    fun setTemp(temp: String)
    fun setBackground(color: Int)
    fun setHourlyImage(image: Int)
    fun setWeatherImage(weatherImage: Int)
    fun setHourlyImageGone(isGone: Int)
}