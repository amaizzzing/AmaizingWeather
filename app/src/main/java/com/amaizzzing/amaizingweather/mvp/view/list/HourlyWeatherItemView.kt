package com.amaizzzing.amaizingweather.mvp.view.list

interface HourlyWeatherItemView: IItemView {
    fun setTemp(temp: String)

    fun setTime(time: String)

    fun setImage(image: Int)

    fun setWind(wind: String)
}