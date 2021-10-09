package com.amaizzzing.amaizingweather.mvp.presenter

import com.amaizzzing.amaizingweather.mvp.navigation.IScreens
import com.amaizzzing.amaizingweather.mvp.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.mainWeather())
    }

    fun backClicked() {
        router.exit()
    }
}