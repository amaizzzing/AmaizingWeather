package com.amaizzzing.amaizingweather.mvp.presenter

import com.amaizzzing.amaizingweather.mvp.models.entity.list.CityModel
import com.amaizzzing.amaizingweather.mvp.models.entity.list.model_factory.CityModelFactory
import com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.city.GeonameCity
import com.amaizzzing.amaizingweather.mvp.models.repositories.retrofit.IRetrofitCityRepository
import com.amaizzzing.amaizingweather.mvp.models.repositories.retrofit.RetrofitCityRepository
import com.amaizzzing.amaizingweather.mvp.models.repositories.room.IHistoryCache
import com.amaizzzing.amaizingweather.mvp.navigation.IScreens
import com.amaizzzing.amaizingweather.mvp.presenter.list.ISearchCityListPresenter
import com.amaizzzing.amaizingweather.mvp.view.SearchCityView
import com.amaizzzing.amaizingweather.mvp.view.list.SearchCityItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class SearchCityPresenter: MvpPresenter<SearchCityView>(),
    RetrofitCityRepository.CityRepositoryCallback {
    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var repo: IRetrofitCityRepository

    @Inject
    lateinit var historyCache: IHistoryCache

    @field:Named("uiScheduler")
    @Inject
    lateinit var uiScheduler: Scheduler

    private val compositeDisposable = CompositeDisposable()

    private val cityModelFactory = CityModelFactory()

    class SearchCityListPresenter : ISearchCityListPresenter {
        val cityList = mutableListOf<CityModel>()
        override var itemClickListener: ((SearchCityItemView) -> Unit)? = null

        override fun getCount() = cityList.size

        override fun bindView(view: SearchCityItemView) {
            val city = cityList[view.pos]
            city.fullCityName.let { view.setCity(it) }
        }
    }

    val searchCityListPresenter = SearchCityListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()

        searchCityListPresenter.itemClickListener = { itemView ->
            viewState.sendChosenCity(searchCityListPresenter.cityList[itemView.pos])
            backPressed()
        }
    }

    fun makeSearch_tempForTests(query: String) {
        viewState.showLoad()
        repo.getCityByName_tempForTests(query, this)
    }

    fun makeSearch(query: String) {
        compositeDisposable.add(
            repo.getCityByName(query)
                .subscribeOn(Schedulers.io())
                .observeOn(uiScheduler)
                .subscribe(
                    {
                        if (it.isNotEmpty()) {
                            searchCityListPresenter.cityList.clear()
                            searchCityListPresenter.cityList.addAll(it)
                            viewState.updateList()
                            viewState.endLoad()
                        } else {
                            viewState.notResult()
                        }
                    },
                    {
                        println("Error: ${it.message}")
                    }
                )
        )
    }

    fun firstLoad() {
        viewState.showLoad()
        compositeDisposable.add(
            historyCache.getLastCity(10)
                .observeOn(uiScheduler)
                .subscribe(
                    {
                        if (it.isNotEmpty()) {
                            searchCityListPresenter.cityList.clear()
                            searchCityListPresenter.cityList.addAll(it)
                            viewState.updateList()
                            viewState.endLoad()
                        } else {
                            viewState.notResult()
                        }
                    },
                    {
                        println("Error: ${it.message}")
                    }
                )
        )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun handleCityResponse(response: Response<List<GeonameCity>?>?) {
        viewState.showLoad()

        if (response != null && response.isSuccessful) {
            val searchResults = response.body()
            if (searchResults != null && searchResults.isNotEmpty()) {
                searchCityListPresenter.cityList.clear()
                searchCityListPresenter.cityList.addAll(
                    searchResults.map { item ->
                        cityModelFactory.getCityModelFromResponse(item)
                    }
                )
                viewState.updateList()
                viewState.endLoad()
            } else {
                viewState.notResult()
            }
        } else {
            viewState.notResult()
        }
    }

    override fun handleCityError() {
        viewState.endLoad()
        viewState.notResult()
    }
}