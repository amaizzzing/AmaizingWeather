package com.amaizzzing.amaizingweather.mvp.presenter

import com.amaizzzing.amaizingweather.mvp.models.entity.list.DailyWeatherModel
import com.amaizzzing.amaizingweather.mvp.models.entity.list.HourlyWeatherModel
import com.amaizzzing.amaizingweather.mvp.models.repositories.room.IHourlyWeatherCache
import com.amaizzzing.amaizingweather.mvp.navigation.IScreens
import com.amaizzzing.amaizingweather.mvp.presenter.list.IHourlyWeatherListPresenter
import com.amaizzzing.amaizingweather.mvp.view.HourlyWeatherView
import com.amaizzzing.amaizingweather.mvp.view.list.HourlyWeatherItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import javax.inject.Inject
import javax.inject.Named

class HourlyWeatherPresenter(val idCity: Long?, val dailyWeatherModel: DailyWeatherModel?): MvpPresenter<HourlyWeatherView>() {
    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var hourlyCache: IHourlyWeatherCache

    @field:Named("uiScheduler")
    @Inject
    lateinit var uiScheduler: Scheduler

    private val compositeDisposable = CompositeDisposable()

    class HourlyWeatherListPresenter : IHourlyWeatherListPresenter {
        val hourlyList = mutableListOf<HourlyWeatherModel>()
        override var itemClickListener: ((HourlyWeatherItemView) -> Unit)? = null

        override fun getCount() = hourlyList.size

        override fun bindView(view: HourlyWeatherItemView) {
            val hourly = hourlyList[view.pos]
            view.setTemp(hourly.temp)
            view.setTime(hourly.time)
            view.setWind(hourly.wind)
            view.setImage(hourly.image)
        }
    }

    val hourlyWeatherListPresenter = HourlyWeatherListPresenter()

    fun loadData() {
        if (idCity != null && dailyWeatherModel != null) {
            compositeDisposable.add(
                hourlyCache.getHourlyForDay(idCity, dailyWeatherModel.dtInMillis)
                    .observeOn(uiScheduler)
                    .subscribe(
                        { hourlyWeather ->
                            hourlyWeatherListPresenter.hourlyList.clear()
                            hourlyWeatherListPresenter.hourlyList.addAll(hourlyWeather)
                            viewState.updateList()
                        },
                        {
                            println("Error: ${it.message}")
                        }
                    )
            )
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}