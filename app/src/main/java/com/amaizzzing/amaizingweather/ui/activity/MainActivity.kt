package com.amaizzzing.amaizingweather.ui.activity

import android.os.Bundle
import com.amaizzzing.amaizingweather.AmaizingWeatherApp
import com.amaizzzing.amaizingweather.R
import com.amaizzzing.amaizingweather.databinding.ActivityMainBinding
import com.amaizzzing.amaizingweather.mvp.presenter.MainPresenter
import com.amaizzzing.amaizingweather.mvp.view.MainView
import com.amaizzzing.amaizingweather.ui.BackButtonListener
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.gms.ads.MobileAds
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter {
        MainPresenter().apply {
            AmaizingWeatherApp.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AmaizingWeatherApp.instance.appComponent.inject(this)

        MobileAds.initialize(this) {}
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is BackButtonListener && it.backPressed()){
                return
            }
        }
        presenter.backClicked()
    }
}