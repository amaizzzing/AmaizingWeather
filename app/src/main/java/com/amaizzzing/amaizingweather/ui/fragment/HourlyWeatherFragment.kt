package com.amaizzzing.amaizingweather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.amaizzzing.amaizingweather.AmaizingWeatherApp
import com.amaizzzing.amaizingweather.databinding.FragmentHourlyWeatherBinding
import com.amaizzzing.amaizingweather.mvp.models.entity.list.DailyWeatherModel
import com.amaizzzing.amaizingweather.mvp.models.room.db.WeatherDatabase
import com.amaizzzing.amaizingweather.mvp.presenter.HourlyWeatherPresenter
import com.amaizzzing.amaizingweather.mvp.view.HourlyWeatherView
import com.amaizzzing.amaizingweather.ui.BackButtonListener
import com.amaizzzing.amaizingweather.ui.adapter.HourlyWeatherAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class HourlyWeatherFragment: MvpAppCompatFragment(), HourlyWeatherView, BackButtonListener {
    @Inject
    lateinit var database: WeatherDatabase

    companion object {
        private const val DAILY_ARG = "DAILY_ARG"
        private const val ID_CITY = "ID_CITY"

        fun newInstance(idCity: Long, dailyWeather: DailyWeatherModel) = HourlyWeatherFragment().apply {
            arguments = Bundle().apply {
                putParcelable(DAILY_ARG, dailyWeather)
                putLong(ID_CITY, idCity)
            }
        }
    }

    var adapter: HourlyWeatherAdapter? = null
    private var vb: FragmentHourlyWeatherBinding? = null

    val presenter: HourlyWeatherPresenter by moxyPresenter {
        val dailyWeatherModel = arguments?.getParcelable<DailyWeatherModel>(DAILY_ARG)
        val idCity = arguments?.getLong(ID_CITY)
        HourlyWeatherPresenter(idCity, dailyWeatherModel).apply {
            AmaizingWeatherApp.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHourlyWeatherBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun init() {
        vb?.rvHourlyWeather?.layoutManager = LinearLayoutManager(context)
        adapter = HourlyWeatherAdapter(presenter.hourlyWeatherListPresenter).apply {
            AmaizingWeatherApp.instance.appComponent.inject(this)
        }
        vb?.rvHourlyWeather?.adapter = adapter

        presenter.loadData()
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed() = presenter.backPressed()
}