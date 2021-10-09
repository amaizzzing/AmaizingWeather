package com.amaizzzing.amaizingweather.mvp.presenter

import android.graphics.Color
import com.amaizzzing.amaizingweather.mvp.models.entity.list.CityModel
import com.amaizzzing.amaizingweather.mvp.models.entity.list.DailyWeatherModel
import com.amaizzzing.amaizingweather.mvp.models.repositories.retrofit.IRetrofitWeatherRepository
import com.amaizzzing.amaizingweather.mvp.models.repositories.room.IHistoryCache
import com.amaizzzing.amaizingweather.mvp.navigation.IScreens
import com.amaizzzing.amaizingweather.mvp.presenter.list.IDailyWeatherListPresenter
import com.amaizzzing.amaizingweather.mvp.view.MainWeatherView
import com.amaizzzing.amaizingweather.mvp.view.list.DailyWeatherItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import javax.inject.Inject
import javax.inject.Named

class MainWeatherPresenter: MvpPresenter<MainWeatherView>() {
    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var repo: IRetrofitWeatherRepository

    @Inject
    lateinit var historyCache: IHistoryCache

    @field:Named("uiScheduler")
    @Inject
    lateinit var uiScheduler: Scheduler

    private var currentCity: CityModel? = null

    private val compositeDisposable = CompositeDisposable()

    class DailyWeatherPresenter : IDailyWeatherListPresenter {
        val weatherList = mutableListOf<DailyWeatherModel>()
        override var itemClickListener: ((DailyWeatherItemView) -> Unit)? = null
        override var hourlyClickListener: ((DailyWeatherItemView) -> Unit)? = null

        override fun getCount() = weatherList.size

        override fun bindView(view: DailyWeatherItemView) {
            val weather = weatherList[view.pos]

            weather.temp.let { view.setTemp(it) }

            weather.dt.let { view.setDate(it) }

            view.setHourlyImage(weather.hourlyForecastImage)

            view.setWeatherImage(weather.imageDaily)

            view.setHourlyImageGone(weather.isHourlyImageGone)

            view.setBackground(weather.backgroundColor)
        }
    }

    val dailyWeatherPresenter = DailyWeatherPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()

        dailyWeatherPresenter.itemClickListener = { itemView ->
            dailyWeatherPresenter.weatherList.forEach { it.backgroundColor = Color.TRANSPARENT }
            dailyWeatherPresenter.weatherList[itemView.pos].backgroundColor = Color.YELLOW
            viewState.updateDailyWeather(currentCity?.cityName ?: "", dailyWeatherPresenter.weatherList[itemView.pos])
            viewState.updateList()
        }

        dailyWeatherPresenter.hourlyClickListener = { itemView ->
            val day = dailyWeatherPresenter.weatherList[itemView.pos]
            router.navigateTo(screens.hourlyWeather(currentCity?.cityId ?: 0, day))
        }
    }

    fun firstLoadData() {
        compositeDisposable.add(
            historyCache.getLastCity(1).subscribe(
                {
                    it?.let {
                        loadData(it.first())
                    }
                },
                {
                    println("Error: ${it.message}")
                }
            )
        )
    }

    fun loadData(city: CityModel?) {
        if (city != null) {
            currentCity = city
        }
        currentCity?.let {
            viewState.showLoad()
            compositeDisposable.add(
                repo.getWeather(it.cityId, it.lat, it.lon)
                    .observeOn(uiScheduler)
                    .subscribe(
                        { weatherResponse ->
                            dailyWeatherPresenter.weatherList.clear()
                            dailyWeatherPresenter.weatherList.addAll(weatherResponse)
                            viewState.updateList()

                            if (weatherResponse.isNotEmpty()) {
                                viewState.updateDailyWeather(currentCity?.cityName ?: "", weatherResponse.first())
                            }

                            if(city == null) {
                                viewState.showRefreshSnackbar()
                            }
                            viewState.endLoad()
                        },
                        {
                            println("Error: ${it.message}")
                        }
                    )
            )
        }
    }

    fun openSearchCityFragment() {
        router.navigateTo(screens.searchCity())
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