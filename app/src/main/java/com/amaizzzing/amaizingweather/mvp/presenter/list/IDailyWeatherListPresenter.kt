package com.amaizzzing.amaizingweather.mvp.presenter.list

import com.amaizzzing.amaizingweather.mvp.view.list.DailyWeatherItemView

interface IDailyWeatherListPresenter: IListPresenter<DailyWeatherItemView> {
    var hourlyClickListener:((DailyWeatherItemView) -> Unit)?
}