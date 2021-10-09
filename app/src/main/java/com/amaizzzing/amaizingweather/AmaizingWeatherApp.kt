package com.amaizzzing.amaizingweather

import android.app.Application
import com.amaizzzing.amaizingweather.di.AppComponent
import com.amaizzzing.amaizingweather.di.DaggerAppComponent
import com.amaizzzing.amaizingweather.di.modules.AppModule
import com.amaizzzing.amaizingweather.mvp.models.room.db.WeatherDatabase

class AmaizingWeatherApp: Application() {
    companion object {
        lateinit var instance: AmaizingWeatherApp
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}