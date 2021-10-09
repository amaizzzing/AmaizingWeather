package com.amaizzzing.amaizingweather.di

import com.amaizzzing.amaizingweather.di.modules.*
import com.amaizzzing.amaizingweather.mvp.models.entity.list.model_factory.DailyWeatherModelFactory
import com.amaizzzing.amaizingweather.mvp.models.entity.list.model_factory.HourlyWeatherModelFactory
import com.amaizzzing.amaizingweather.mvp.presenter.HourlyWeatherPresenter
import com.amaizzzing.amaizingweather.mvp.presenter.MainPresenter
import com.amaizzzing.amaizingweather.mvp.presenter.MainWeatherPresenter
import com.amaizzzing.amaizingweather.mvp.presenter.SearchCityPresenter
import com.amaizzzing.amaizingweather.ui.activity.MainActivity
import com.amaizzzing.amaizingweather.ui.adapter.DailyWeatherAdapter
import com.amaizzzing.amaizingweather.ui.adapter.HourlyWeatherAdapter
import com.amaizzzing.amaizingweather.ui.adapter.SearchCityAdapter
import com.amaizzzing.amaizingweather.ui.fragment.MainWeatherFragment
import com.amaizzzing.amaizingweather.ui.fragment.SearchCityFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        CiceroneModule::class,
        ImageModule::class,
        CacheModule::class,
        RepoModule::class,
        ModelsModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(mainWeatherFragment: MainWeatherFragment)

    fun inject(searchCityFragment: SearchCityFragment)

    fun inject(mainPresenter: MainPresenter)

    fun inject(mainWeatherPresenter: MainWeatherPresenter)

    fun inject(searchCityPresenter: SearchCityPresenter)

    fun inject(hourlyWeatherPresenter: HourlyWeatherPresenter)

    fun inject(usersRVAdapter: DailyWeatherAdapter)

    fun inject(searchCityAdapter: SearchCityAdapter)

    fun inject(hourlyWeatherAdapter: HourlyWeatherAdapter)

    fun inject(dailyWeatherModelFactory: DailyWeatherModelFactory)

    fun inject(hourlyWeatherModelFactory: HourlyWeatherModelFactory)
}