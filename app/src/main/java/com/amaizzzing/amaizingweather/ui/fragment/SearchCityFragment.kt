package com.amaizzzing.amaizingweather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.amaizzzing.amaizingweather.AmaizingWeatherApp
import com.amaizzzing.amaizingweather.Constants.CHOOSE_CITY_KEY
import com.amaizzzing.amaizingweather.Constants.REQUEST_KEY_CHOOSE_CITY
import com.amaizzzing.amaizingweather.RxSearchFlowable
import com.amaizzzing.amaizingweather.databinding.FragmentSearchCityBinding
import com.amaizzzing.amaizingweather.mvp.models.entity.list.CityModel
import com.amaizzzing.amaizingweather.mvp.models.room.db.WeatherDatabase
import com.amaizzzing.amaizingweather.mvp.presenter.SearchCityPresenter
import com.amaizzzing.amaizingweather.mvp.view.SearchCityView
import com.amaizzzing.amaizingweather.ui.BackButtonListener
import com.amaizzzing.amaizingweather.ui.adapter.SearchCityAdapter
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Named

class SearchCityFragment: MvpAppCompatFragment(), SearchCityView, BackButtonListener {
    @Inject
    lateinit var database: WeatherDatabase

    @field:Named("uiScheduler")
    @Inject
    lateinit var uiScheduler: Scheduler

    companion object {
        fun newInstance() = SearchCityFragment()
    }

    var adapter: SearchCityAdapter? = null
    private var vb: FragmentSearchCityBinding? = null

    val presenter: SearchCityPresenter by moxyPresenter {
        SearchCityPresenter().apply {
            AmaizingWeatherApp.instance.appComponent.inject(this)
        }
    }

    init {
        AmaizingWeatherApp.instance.appComponent.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = FragmentSearchCityBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun init() {
        vb?.rvCitiesFragment?.layoutManager = LinearLayoutManager(context)
        adapter = SearchCityAdapter(presenter.searchCityListPresenter).apply {
            AmaizingWeatherApp.instance.appComponent.inject(this)
        }
        vb?.rvCitiesFragment?.adapter = adapter

        vb?.searchViewLandscapeCitiesChoose?.let {
            RxSearchFlowable.searchFromView(vb?.searchViewLandscapeCitiesChoose!!)
                .subscribeOn(Schedulers.io())
                .observeOn(uiScheduler)
                .subscribe { text ->
                    showLoad()
                    presenter.makeSearch(text)
                }
        }

        presenter.firstLoad()
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun sendChosenCity(city: CityModel) {
        parentFragmentManager.setFragmentResult(
            REQUEST_KEY_CHOOSE_CITY,
            bundleOf(CHOOSE_CITY_KEY to city)
        )
    }

    override fun showLoad() {
        vb?.pbSearchLoad?.visibility = View.VISIBLE
        vb?.rvCitiesFragment?.visibility = View.GONE
        vb?.notResultTextView?.visibility = View.GONE
    }

    override fun endLoad() {
        vb?.pbSearchLoad?.visibility = View.GONE
        vb?.rvCitiesFragment?.visibility = View.VISIBLE
        vb?.notResultTextView?.visibility = View.GONE
    }

    override fun notResult() {
        vb?.pbSearchLoad?.visibility = View.GONE
        vb?.rvCitiesFragment?.visibility = View.GONE
        vb?.notResultTextView?.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed() = presenter.backPressed()
}