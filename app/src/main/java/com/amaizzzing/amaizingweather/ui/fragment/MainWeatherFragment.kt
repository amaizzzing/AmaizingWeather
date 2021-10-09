package com.amaizzzing.amaizingweather.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.util.Preconditions
import androidx.recyclerview.widget.LinearLayoutManager
import com.amaizzzing.amaizingweather.AmaizingWeatherApp
import com.amaizzzing.amaizingweather.Constants.CHOOSE_CITY_KEY
import com.amaizzzing.amaizingweather.Constants.REQUEST_KEY_CHOOSE_CITY
import com.amaizzzing.amaizingweather.R
import com.amaizzzing.amaizingweather.databinding.FragmentMainWeatherBinding
import com.amaizzzing.amaizingweather.mvp.models.entity.list.CityModel
import com.amaizzzing.amaizingweather.mvp.models.entity.list.DailyWeatherModel
import com.amaizzzing.amaizingweather.mvp.models.image.IImageLoader
import com.amaizzzing.amaizingweather.mvp.models.room.db.WeatherDatabase
import com.amaizzzing.amaizingweather.mvp.presenter.MainWeatherPresenter
import com.amaizzzing.amaizingweather.mvp.view.MainWeatherView
import com.amaizzzing.amaizingweather.ui.BackButtonListener
import com.amaizzzing.amaizingweather.ui.adapter.DailyWeatherAdapter
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainWeatherFragment: MvpAppCompatFragment(), MainWeatherView, BackButtonListener {
    companion object {
        fun newInstance() = MainWeatherFragment()
    }

    @Inject
    lateinit var database: WeatherDatabase

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    @Inject
    lateinit var imageBackgroundLoader: IImageLoader<ViewGroup>

    val presenter: MainWeatherPresenter by moxyPresenter {
        MainWeatherPresenter().apply {
            AmaizingWeatherApp.instance.appComponent.inject(this)
        }
    }

    var adapter: DailyWeatherAdapter? = null
    private var vb: FragmentMainWeatherBinding? = null

    init {
        AmaizingWeatherApp.instance.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentMainWeatherBinding.inflate(inflater, container, false).also {
        vb = it
        parentFragmentManager.setFragmentResultListener(
            REQUEST_KEY_CHOOSE_CITY,
            this,
            {
                requestKey, result ->
                    onFragmentResult(requestKey, result)
            }
        )
        vb?.refreshWeather?.setOnClickListener {
            presenter.loadData(null)
        }
    }.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.firstLoadData()
    }

    @SuppressLint("RestrictedApi")
    private fun onFragmentResult(requestKey: String, result: Bundle) {
        Preconditions.checkState(REQUEST_KEY_CHOOSE_CITY == requestKey)

        val city = result.getParcelable<CityModel>(CHOOSE_CITY_KEY)
        city?.let {
            presenter.loadData(city)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvListWeatherFragment?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = DailyWeatherAdapter(presenter.dailyWeatherPresenter).apply {
            AmaizingWeatherApp.instance.appComponent.inject(this)
        }
        vb?.rvListWeatherFragment?.adapter = adapter

        vb?.currentCityWeatherFragment?.setOnClickListener {
            presenter.openSearchCityFragment()
        }
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun updateDailyWeather(cityName: String, dailyWeatherModel: DailyWeatherModel) {
        vb?.tempWeatherFragment?.text = dailyWeatherModel.temp
        vb?.feelsWeatherFragment?.text = dailyWeatherModel.feelsLike
        vb?.humidityWeatherFragment?.text = dailyWeatherModel.humidity
        vb?.windWeatherFragment?.text = dailyWeatherModel.windSpeed
        vb?.sunriceWeatherFragment?.text = dailyWeatherModel.sunrise
        vb?.sunsetWeatherFragment?.text = dailyWeatherModel.sunset
        vb?.typeWeatherWeatherFragment?.text = dailyWeatherModel.weather
        vb?.currentCityWeatherFragment?.text = cityName.capitalize()
        imageLoader.loadInto(dailyWeatherModel.imageDaily, vb?.mainWeatherImage!!)
        imageBackgroundLoader.loadInto(dailyWeatherModel.mainImage, vb?.rootMainWeather!!)
    }

    override fun showRefreshSnackbar() {
        Snackbar.make(vb?.windWeatherFragment!!, R.string.title_weather_update, LENGTH_SHORT).show()
    }

    override fun showLoad() {
        vb?.mainWeatherContent?.visibility = View.GONE
        vb?.pbSearchWeather?.visibility = View.VISIBLE
    }

    override fun endLoad() {
        vb?.mainWeatherContent?.visibility = View.VISIBLE
        vb?.pbSearchWeather?.visibility = View.GONE
    }

    override fun backPressed() = presenter.backPressed()
}