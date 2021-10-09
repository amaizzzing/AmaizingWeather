package com.amaizzzing.amaizingweather.di.modules

import com.amaizzzing.amaizingweather.AmaizingWeatherApp
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Named

@Module
class AppModule(val app: AmaizingWeatherApp) {
    @Provides
    fun app(): AmaizingWeatherApp = app

    @Named("uiScheduler")
    @Provides
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}